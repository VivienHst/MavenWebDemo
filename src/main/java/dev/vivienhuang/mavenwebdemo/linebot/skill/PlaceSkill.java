package dev.vivienhuang.mavenwebdemo.linebot.skill;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
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

import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;
import dev.vivienhuang.mavenwebdemo.linebot.message.ReplyMessageModel;
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

@Component
public class PlaceSkill implements IPlaceSkill {

	@Autowired
	IBaseSkill baseSkill;
	
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

	private void getGoogleMapNearbyPlace(double latitude, double longitude, String replyToken, String token) {
		String googleMapPlaceApiUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" + 
				"?location=" + latitude + "," +  longitude +
				"&radius=80" + 
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
			JSONObject resultObject =  jsonArray.getJSONObject(i);
			
			String name = resultObject.getString("name");
	    	System.out.println("name : " + name);
	    	
	    	double rating = resultObject.getDouble("rating");
	    	System.out.println("rating : " + rating);

	    	String vicinity = resultObject.getString("vicinity");
	    	System.out.println("vicinity : " + vicinity);
	    	
	    	String placeId = resultObject.getString("place_id");
	    	
	    	JSONObject location = resultObject.getJSONObject("geometry").getJSONObject("location");
	    	double placeLat = location.getDouble("lat");
	    	double placeLng = location.getDouble("lng");
	    	
	    	String placeUrl = getPlaceGoogleMapUrl(placeLat, placeLng, placeId);
	    	
		    JSONArray photoArray = resultObject.getJSONArray("photos");

	    	if(photoArray.length() > 0) {
	    		String photo_reference = photoArray.getJSONObject(0).getString("photo_reference");
		    	System.out.println("photo_reference : " + photo_reference);
	    		String photoUrl = getMapPhotoByPhotoReferance(photo_reference);
	    		BubbleContent placeContent = createRestaurantBubble(name, rating, vicinity, photoUrl, placeUrl);	    		
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
	
	private String getPlaceGoogleMapUrl(double latitude, double longitude, String placeId) {

		String mapUrl = "https://www.google.com/maps/search/?api=1"
				+ "&query=" + latitude + "," + longitude
				+ "&query_place_id=" + placeId;
		
		return mapUrl;
	}
	
	private BubbleContent createRestaurantBubble(String name, double rating, String vicinity, String photoUrl, String mapUrl) {
		BubbleContent bubbleContent = new BubbleContent();
		
		ImageComponent heroComponent  = new ImageComponent();
		heroComponent.setUrl(photoUrl);
		heroComponent.setSize("full");
		heroComponent.setAspectRatio("20:13");
		heroComponent.setAspectMode("cover");
		bubbleContent.setHero(heroComponent);
		
		BoxComponent bodyComponent = new BoxComponent();
		bodyComponent.setLayout("vertical");
		List<FlexContent> bodyContents = new ArrayList<FlexContent>();
		
		TextComponent titleTextComponent = new TextComponent();
		titleTextComponent.setText(name);
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
		addressPlaceComponent.setText(vicinity);
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
		ratingCountComponent.setText(rating + "顆星");
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
		uriAction.setUri(mapUrl);
		
		placeMapLinkComponent.setStyle("link");
		placeMapLinkComponent.setHeight("sm");
		placeMapLinkComponent.setAction(uriAction);
		
		footerContents.add(placeMapLinkComponent);
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
