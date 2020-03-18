package dev.vivienhuang.mavenwebdemo.linebot.skill;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import dev.vivienhuang.mavenwebdemo.entity.BasicDBMessageVO;
import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;
import dev.vivienhuang.mavenwebdemo.entity.member.LineMemberFavoritePlaceVO;
import dev.vivienhuang.mavenwebdemo.entity.viewmodel.linemessage.LinePlaceVM;
import dev.vivienhuang.mavenwebdemo.linebot.member.MemberStatus;
import dev.vivienhuang.mavenwebdemo.linebot.message.MessageModel;
import dev.vivienhuang.mavenwebdemo.linebot.message.ReplyMessageModel;
import dev.vivienhuang.mavenwebdemo.linebot.message.action.PostbackAction;
import dev.vivienhuang.mavenwebdemo.linebot.message.action.UriAction;
import dev.vivienhuang.mavenwebdemo.linebot.message.flex.BoxComponent;
import dev.vivienhuang.mavenwebdemo.linebot.message.flex.BubbleContent;
import dev.vivienhuang.mavenwebdemo.linebot.message.flex.ButtonComponent;
import dev.vivienhuang.mavenwebdemo.linebot.message.flex.CarouselContent;
import dev.vivienhuang.mavenwebdemo.linebot.message.flex.FlexContent;
import dev.vivienhuang.mavenwebdemo.linebot.message.flex.FlexMessage;
import dev.vivienhuang.mavenwebdemo.linebot.message.flex.ImageComponent;
import dev.vivienhuang.mavenwebdemo.linebot.message.flex.TextComponent;
import dev.vivienhuang.mavenwebdemo.linebot.webhook.EventModel;
import dev.vivienhuang.mavenwebdemo.service.cache.ILineMemberCacheService;
import dev.vivienhuang.mavenwebdemo.service.line_member.ILineMemberFavoritePlaceService;

@Component
public class PlaceSkill implements IPlaceSkill {

	@Autowired
	IBaseSkill baseSkill;

	@Autowired
	ILineMemberFavoritePlaceService lineMemberFavoritePlaceService;
	
	@Autowired 
	ILineMemberCacheService lineMemberCacheService;
	
	@Override
	public boolean startQueryFavoritePlace(EventModel lineEvent, String channelAccessToken) {
		
		if(baseSkill.isTextMessage(lineEvent)) {
			String receiveMessage = lineEvent.getMessage().getText();
			if(receiveMessage.equals("吃什麼")) {
				lineMemberCacheService.getMemberStatus(lineEvent.getSource().getUserId(), MemberStatus.WAIT_FAVORITE_PLACE);
				String replyMessage = "請傳送您的位置資訊";
				List<LineMessage> messageModels = new ArrayList<>();
				messageModels.add(new MessageModel("text", replyMessage));
				baseSkill.sendReplyMessage(new ReplyMessageModel(lineEvent.getReplyToken(), messageModels), channelAccessToken);
				return true;
			}
		}		
		return false;
	}
	
	@Override
	public boolean replyFavoritePlaceSkill(EventModel lineEvent, String channelAccessToken) {
		
		if(!lineMemberCacheService.getMemberStatus(lineEvent.getSource().getUserId()).equals(MemberStatus.WAIT_FAVORITE_PLACE)){
			return false;
		}
		if(lineEvent.getType().equals("message") && lineEvent.getMessage().getType().equals("location")) {
			lineMemberCacheService.getMemberStatus(lineEvent.getSource().getUserId(), MemberStatus.NORMAL);

			CarouselContent carouselContent = new CarouselContent();
		    
		    List<LineMemberFavoritePlaceVO> lineMemberFavoritePlaceVOs = lineMemberFavoritePlaceService.getLineMemberFavoritePlacesByLineId(
		    		lineEvent.getSource().getUserId(), 
		    		lineEvent.getMessage().getLatitude(),
					lineEvent.getMessage().getLongitude());
		    
		    
		    List<FlexContent> bubbleContents = new ArrayList<FlexContent>();
		    
		    for (LineMemberFavoritePlaceVO lineMemberFavoritePlaceVO : lineMemberFavoritePlaceVOs) {		    	
		    	BubbleContent placeContent = null;
				
		    	LinePlaceVM linePlaceVM = new LinePlaceVM(
		    			lineMemberFavoritePlaceVO.getLineMemberFavoritePlacePK().getPlaceId(),
		    			lineMemberFavoritePlaceVO.getPlaceName(), 
		    			lineMemberFavoritePlaceVO.getRating(),
		    			lineMemberFavoritePlaceVO.getAddress(),
		    			lineMemberFavoritePlaceVO.getPhotoUrl(),
		    			lineMemberFavoritePlaceVO.getMapUrl(),
		    			lineMemberFavoritePlaceVO.getLatitude(), 
		    			lineMemberFavoritePlaceVO.getLongitude());
	    		placeContent = createRestaurantBubble(linePlaceVM);
				
				if(placeContent != null) {
					bubbleContents.add(placeContent);
				}
			}
		    
		    carouselContent.setContents(bubbleContents);
		    
		    FlexMessage flexMessage  = new FlexMessage();
			flexMessage.setAltText("最愛餐廳");
			flexMessage.setContents(carouselContent);
			List<LineMessage> placeMessage = new ArrayList<LineMessage>();
			placeMessage.add(flexMessage);
			
			ReplyMessageModel replyMessageModel = new ReplyMessageModel(lineEvent.getReplyToken(), placeMessage);
			baseSkill.sendReplyMessage(replyMessageModel, channelAccessToken);
			return true;
		}		
		return false;
	}
	
	@Override
	public boolean replyEatPlaceSkill(EventModel lineEvent, String channelAccessToken) {
		if(lineEvent.getType().equals("message") && lineEvent.getMessage().getType().equals("location")) {
			getGoogleMapNearbyPlace(
				lineEvent.getMessage().getLatitude(),
				lineEvent.getMessage().getLongitude(), 
				lineEvent.getReplyToken(),
				channelAccessToken);
			return true;
		}
		
		return false;
	}

	
	String googleApiKey = "AIzaSyByFgt8sArSD4O65Nk3Cb_B9up6CntKmuQ";
	// &key=AIzaSyByFgt8sArSD4O65Nk3Cb_B9up6CntKmuQ

	private void getGoogleMapNearbyPlace(double latitude, double longitude, String replyToken, String token) {
		String googleMapPlaceApiUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" + 
				"?location=" + latitude + "," +  longitude +
				"&radius=200" + 
				"&types=restaurant" + 
				"&language=zh-TW" +
				"&fields=photos,formatted_address,name,rating,opening_hours" + 
				"&opennow" +
				"&key=" + googleApiKey;
		
		System.out.println("googleMapPlaceApiUrl : " + googleMapPlaceApiUrl);
		
		
	    String result = getHttpGetResult(googleMapPlaceApiUrl);
	    
	    System.out.println("response.getBody() : " + result);
	    JSONObject jsonObject = new JSONObject(result);
	    JSONArray jsonArray = jsonObject.getJSONArray("results");
	    int resultCount = jsonArray.length();
	    int maxCount = 3;
	    
	    
	    if(resultCount > maxCount) {
	    	resultCount = maxCount;
	    } 
	    
	    
	    CarouselContent carouselContent = new CarouselContent();
	    
	    List<FlexContent> bubbleContents = new ArrayList<FlexContent>();
	    
	    for (int i = 0; i < resultCount; i++) {
	    	
			BubbleContent placeContent = convertPlaceInfoToPlaceContent(jsonArray.getJSONObject(i), latitude, longitude);
			if(placeContent != null) {
				bubbleContents.add(placeContent);
			}
		}
	    carouselContent.setContents(bubbleContents);
	    
	    FlexMessage flexMessage  = new FlexMessage();
		flexMessage.setAltText("附近餐廳資訊");
		flexMessage.setContents(carouselContent);
		List<LineMessage> placeMessage = new ArrayList<LineMessage>();
		placeMessage.add(flexMessage);
		
		ReplyMessageModel replyMessageModel = new ReplyMessageModel(replyToken, placeMessage);
		baseSkill.sendReplyMessage(replyMessageModel, token);
	}
	
	private BubbleContent convertPlaceInfoToPlaceContent(JSONObject jsonObject, double latitude, double longitude) {
		BubbleContent placeContent = null;
		
		try {
			String name = jsonObject.getString("name");
	    	System.out.println("name : " + name);
	    	
	    	double rating = jsonObject.getDouble("rating");
	    	System.out.println("rating : " + rating);

	    	String vicinity = jsonObject.getString("vicinity");
	    	System.out.println("vicinity : " + vicinity);
	    	
	    	String placeId = jsonObject.getString("place_id");
	    	
	    	JSONObject location = jsonObject.getJSONObject("geometry").getJSONObject("location");
	    	double placeLat = location.getDouble("lat");
	    	double placeLng = location.getDouble("lng");
	    	
	    	String mapUrl = getPlaceGoogleMapUrl(placeLat, placeLng, placeId);
	    	
		    JSONArray photoArray = jsonObject.getJSONArray("photos");

	    	if(photoArray.length() > 0) {
	    		String photo_reference = photoArray.getJSONObject(0).getString("photo_reference");
		    	System.out.println("photo_reference : " + photo_reference);
	    		String photoUrl = getMapPhotoByPhotoReferance(photo_reference);
	    		
	    		LinePlaceVM linePlaceVM = new LinePlaceVM(placeId, name, rating, vicinity, photoUrl, mapUrl, latitude, longitude);
	    		placeContent = createRestaurantBubble(linePlaceVM);	 
	    		placeContent = setAddFoodPlaceButton(placeContent, linePlaceVM.getPlaceId());

	    	}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return placeContent;
	}
	
	private String getPlaceGoogleMapUrl(double latitude, double longitude, String placeId) {
		String mapUrl = "https://www.google.com/maps/search/?api=1"
				+ "&query=" + latitude + "," + longitude
				+ "&query_place_id=" + placeId;
	
		return mapUrl;
	}
	
	@Override
	public boolean addFavoriteFoodPlace(LineBotVO lineBotVO, EventModel lineEvent) {
		if(lineEvent.getType().equals("postback") && lineEvent.getPostback().getData().startsWith("addFoodPlace&&")) {
			String placeId = lineEvent.getPostback().getData().split("&&")[1];			
//			createLineFavoritePlaceVO(lineEvent.getSource().getUserId(), placeJson);
			LinePlaceVM linePlaceVM = createLineFavoritePlaceVOByPlaceId(placeId);
			
			if(linePlaceVM != null) {
				createLineFavoritePlaceVO(linePlaceVM, lineEvent.getSource().getUserId());
			}
			return true;
		}
		return false;
	}
	
	private LinePlaceVM createLineFavoritePlaceVOByPlaceId(String placeId) {
		// https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJrTLr-GyuEmsRBfy61i59si0&key=YOUR_API_KEY
		String getPlaceDetailUrl = "https://maps.googleapis.com/maps/api/place/details/json?"
				+ "placeid=" + placeId 
				+ "&key=" + googleApiKey 
				+ "&language=zh-TW"
				+ "&fields=photos,formatted_address,name,rating,opening_hours,geometry/location";
		 String result = getHttpGetResult(getPlaceDetailUrl);
		    
	    System.out.println("response.getBody() : " + result);
	    JSONObject jsonObject = new JSONObject(result);
	    JSONObject resultObject = jsonObject.getJSONObject("result");
	    String placeName = resultObject.getString("name");
	    double rating = resultObject.getDouble("rating");
	    String address = resultObject.getString("formatted_address");
	    
	    JSONObject locationObject = resultObject.getJSONObject("geometry").getJSONObject("location");
	    double latitude = locationObject.getDouble("lat");
	    double longitude = locationObject.getDouble("lng");
	    String mapUrl = getPlaceGoogleMapUrl(latitude, longitude, placeId);
    	
	    JSONArray photoArray = resultObject.getJSONArray("photos");

    	if(photoArray.length() > 0) {
    		String photo_reference = photoArray.getJSONObject(0).getString("photo_reference");
	    	System.out.println("photo_reference : " + photo_reference);
    		String photoUrl = getMapPhotoByPhotoReferance(photo_reference);
    		LinePlaceVM linePlaceVM = new LinePlaceVM(placeId, placeName, rating, address, photoUrl, mapUrl, latitude, longitude);
    		return linePlaceVM;
    	} else {
			return null;
		}			
	}
	
	private void createLineFavoritePlaceVO(LinePlaceVM linePlaceVM, String lineId) {
		LineMemberFavoritePlaceVO lineMemberFavoritePlaceVO = new LineMemberFavoritePlaceVO(
				lineId,
				linePlaceVM.getPlaceId(),
				linePlaceVM.getPlaceName(),
				linePlaceVM.getRating(), 
				linePlaceVM.getAddress(), 
				linePlaceVM.getPhotoUrl(),
				linePlaceVM.getMapUrl(), 
				linePlaceVM.getLatitude(),
				linePlaceVM.getLongitude(), 
				1,
				new java.sql.Timestamp(System.currentTimeMillis()));
		lineMemberFavoritePlaceService.createLineMemberFavoritePlace(lineMemberFavoritePlaceVO);
	}
//	
	private BubbleContent createRestaurantBubble(LinePlaceVM linePlaceVM) {
		BubbleContent bubbleContent = new BubbleContent();
		
		ImageComponent heroComponent  = new ImageComponent();
		heroComponent.setUrl(linePlaceVM.getPhotoUrl());
		heroComponent.setSize("full");
		heroComponent.setAspectRatio("20:13");
		heroComponent.setAspectMode("cover");
		bubbleContent.setHero(heroComponent);
		
		BoxComponent bodyComponent = new BoxComponent();
		bodyComponent.setLayout("vertical");
		List<FlexContent> bodyContents = new ArrayList<FlexContent>();
		
		TextComponent titleTextComponent = new TextComponent();
		titleTextComponent.setText(linePlaceVM.getPlaceName());
		titleTextComponent.setWeight("bold");
		titleTextComponent.setSize("xl");
		bodyContents.add(titleTextComponent);
		
		// ----- 顯示地址 -----
		BoxComponent addressBox = new BoxComponent();
		addressBox.setLayout("vertical");
		addressBox.setMargin("lg");
		addressBox.setSpacing("sm");
		
		TextComponent addressTitleComponent = new TextComponent();
		addressTitleComponent.setText("地址");
		addressTitleComponent.setSize("sm");
		addressTitleComponent.setFlex(1);
		addressTitleComponent.setColor("#aaaaaa");
		
		TextComponent addressPlaceComponent = new TextComponent();
		addressPlaceComponent.setText(linePlaceVM.getAddress());
		addressPlaceComponent.setSize("sm");
		addressPlaceComponent.setFlex(5);
		addressPlaceComponent.setColor("#666666");
		
		List<FlexContent> addressContents = new ArrayList<FlexContent>();
		
		addressContents.add(addressTitleComponent);
		addressContents.add(addressPlaceComponent);
		addressBox.setContents(addressContents);
		bodyContents.add(addressBox);
		
		// ----- 顯示評價 -----
		BoxComponent ratingBox = new BoxComponent();
		ratingBox.setLayout("vertical");
		ratingBox.setMargin("lg");
		ratingBox.setSpacing("sm");

		TextComponent ratingTitleComponent = new TextComponent();
		ratingTitleComponent.setText("評價");
		ratingTitleComponent.setSize("sm");
		ratingTitleComponent.setFlex(1);
		ratingTitleComponent.setColor("#aaaaaa");
		
		TextComponent ratingCountComponent = new TextComponent();
		ratingCountComponent.setText(linePlaceVM.getRating() + "顆星");
		ratingCountComponent.setSize("sm");
		ratingCountComponent.setFlex(5);
		ratingCountComponent.setColor("#666666");
		
		List<FlexContent> ratingContents = new ArrayList<FlexContent>();
		
		ratingContents.add(ratingTitleComponent);
		ratingContents.add(ratingCountComponent);
		ratingBox.setContents(ratingContents);
		bodyContents.add(ratingBox);
		bodyComponent.setContents(bodyContents);
		bubbleContent.setBody(bodyComponent);
		
		// set footer
		
		BoxComponent footerComponent = new BoxComponent();
		footerComponent.setLayout("vertical");
		List<FlexContent> footerContents = new ArrayList<FlexContent>();
		
		ButtonComponent placeMapLinkComponent = new ButtonComponent();
		
		UriAction uriAction = new UriAction();
		uriAction.setLabel("查看地圖");
		uriAction.setUri(linePlaceVM.getMapUrl());
		
		placeMapLinkComponent.setStyle("link");
		placeMapLinkComponent.setHeight("sm");
		placeMapLinkComponent.setAction(uriAction);
		
		// todo add favorite
		
//		ButtonComponent addFrvoriteComponent = new ButtonComponent();
		
//		PostbackAction placePostback = new PostbackAction();
//		placePostback.setLabel("加入我的地點");
//		placePostback.setData("addFoodPlace&&" + linePlaceVM.getPlaceId());
//		
//		addFrvoriteComponent.setStyle("link");
//		addFrvoriteComponent.setHeight("sm");
//		addFrvoriteComponent.setAction(placePostback);
//		
//		footerContents.add(placeMapLinkComponent);
//		footerContents.add(addFrvoriteComponent);
		footerContents.add(placeMapLinkComponent);
		footerComponent.setContents(footerContents);
		bubbleContent.setFooter(footerComponent);
		return bubbleContent;
	}
	
	private BubbleContent setAddFoodPlaceButton(BubbleContent bubbleContent, String placeId) {
		
		ButtonComponent addFrvoriteComponent = new ButtonComponent();
		
		PostbackAction placePostback = new PostbackAction();
		placePostback.setLabel("加入我的地點");
		placePostback.setData("addFoodPlace&&" + placeId);
		
		addFrvoriteComponent.setStyle("link");
		addFrvoriteComponent.setHeight("sm");
		addFrvoriteComponent.setAction(placePostback);
		
		BoxComponent footerComponent = (BoxComponent) bubbleContent.getFooter();
		
		List<FlexContent> footerContents = footerComponent.getContents();
		footerContents.add(addFrvoriteComponent);
		
		footerComponent.setContents(footerContents);
		bubbleContent.setFooter(footerComponent);
		return bubbleContent; 
	}
	
	private String getMapPhotoByPhotoReferance(String photoreference) {
		String googlePlacePhotoApi = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400"
				+ "&photoreference=" + photoreference + "&key=" + googleApiKey;
		return googlePlacePhotoApi;
//	    String result = getHttpGetResult(googlePlacePhotoApi);
//		return result;
	}
	
	private String getHttpGetResult(String url) {
		System.out.println("getHttpGetResult url : " + url);
		
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());	    
		restTemplate.getMessageConverters().set(1, 
				new StringHttpMessageConverter(StandardCharsets.UTF_8));
		
		HttpHeaders headers = new HttpHeaders();
		
	    HttpEntity<String> request = new HttpEntity<String>(headers);
	    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request , String.class);
	    String result = response.getBody();
	    return result;
	}

	

	
}
