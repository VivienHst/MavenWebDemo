package dev.vivienhuang.mavenwebdemo.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;
import dev.vivienhuang.mavenwebdemo.linebot.message.action.ActionObject;
import dev.vivienhuang.mavenwebdemo.linebot.message.action.MessageAction;
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
import dev.vivienhuang.mavenwebdemo.linebot.message.template.ButtonsTemplate;
import dev.vivienhuang.mavenwebdemo.linebot.message.template.TemplateMessage;
import dev.vivienhuang.mavenwebdemo.linebot.skill.IBaseSkill;
import dev.vivienhuang.mavenwebdemo.service.linebot.ILineBotService;
import dev.vivienhuang.mavenwebdemo.util.image.ImageUploadUtil;

@Controller
public class MessageController {
//	@GetMapping("/message")
//	public String getMessagePage(Model model) {
//		return "message_add";
//	}
	@Autowired
	ILineBotService lineBotService;
	
	@Autowired
 	IBaseSkill baseSkill;
	
	@GetMapping("/message")
	public String getOneMemberMessagePage(Model model, String lineId) {
		System.out.println("LineId : " + lineId);
		model.addAttribute("lineId", lineId);

		return "message_add";
	}
	
	@GetMapping("/newTextMessage")
	public String addTextMessagePage(Model model, String lineId) {
		System.out.println("LineId : " + lineId);
		model.addAttribute("lineId", lineId);

		return "message_text_message_add";
	}
	
	@GetMapping("/newImageMessage")
	public String addImageMessagePage(Model model, String lineId) {
		System.out.println("LineId : " + lineId);
		model.addAttribute("lineId", lineId);

		return "message_image_message_add";
	}
	
	@GetMapping("/newTemplateMessage")
	public String addTemplateMessagePage(Model model, String lineId) {
		System.out.println("LineId : " + lineId);
		model.addAttribute("lineId", lineId);

		return "message_template_message_add";
	}
	
	@PostMapping("/sendTextMessage")
	public String sendTextMessagePage(Model model, String lineId, String textMessage) {
		
		System.out.println("lineId : " + lineId);
		System.out.println("textMessage : " + textMessage);

		if(!lineId.isEmpty() && !textMessage.isEmpty()) {
			LineBotVO lineBotVO = lineBotService.getLineBotByMemberLineId(lineId);
			if(lineBotVO != null) {
				baseSkill.pushTextMessage(lineId, textMessage, lineBotVO.getToken());
			}
		}
		return "message_add";
	}
	
	@PostMapping("/sendTemplateMessage")
	public String sendTemplateMessagePage(Model model, String lineId, String textMessage) {
		
		System.out.println("lineId : " + lineId);
		System.out.println("textMessage : " + textMessage);
		
		List<ActionObject> actionObjects = new ArrayList<ActionObject>();
		
		MessageAction weatherAction = new MessageAction("天氣指令", "天氣");
		PostbackAction keywordAction = new PostbackAction("關鍵字指令", "keywordSkill");
		actionObjects.add(weatherAction);
		actionObjects.add(keywordAction);
		
		ButtonsTemplate buttonsTemplates = new ButtonsTemplate();
		buttonsTemplates.setText("可用指令");
		buttonsTemplates.setActions(actionObjects);
		
		TemplateMessage templateMessage = new TemplateMessage();
		
		templateMessage.setAltText(textMessage);
		templateMessage.setTemplate(buttonsTemplates);
		
		List<LineMessage> messages = new ArrayList<LineMessage>();

		messages.add(templateMessage);
		if(!lineId.isEmpty() && !textMessage.isEmpty()) {
			LineBotVO lineBotVO = lineBotService.getLineBotByMemberLineId(lineId);
			if(lineBotVO != null) {
				baseSkill.sendPushMessage(lineId, messages, lineBotVO.getToken());
			}
		}
		return "message_add";
	}
	
	
	@PostMapping("/sendImageMessage")
	public String sendImageMessage(String lineId, MultipartFile[] imageFiles, String imageUrl, Model model) {
//		String imageTitle = model.
		System.out.println("lineId : " + lineId);
		
		if(!lineId.isEmpty()) {
			LineBotVO lineBotVO = lineBotService.getLineBotByMemberLineId(lineId);
			if(lineBotVO != null) {
				if(!imageUrl.isEmpty()) {
					baseSkill.pushImageMessage(lineId, imageUrl, lineBotVO.getToken());
				} else if (imageFiles!=null && imageFiles.length > 0) {
					System.out.println("imageFiles : " + imageFiles.length);
					imageUrl = uploadImageFiles("image" + System.currentTimeMillis(), imageFiles);
			        model.addAttribute("imageUrl", imageUrl);
				}
				System.out.println("imageUrl : " + imageUrl);
			}
		}
		return "message_add";
	}
	
	
	
	@PostMapping("/uploadImageMessage")
	public String uploadImageMessage(String imageTitle, MultipartFile[] imageFiles, String imageUrl, Model model) {
//		String imageTitle = model.
		System.out.println("imageTitle : " + imageTitle);
		model.addAttribute("imageTitle", imageTitle);
		if(imageFiles!=null && imageFiles.length > 0) {
			String imgurImageUrl = uploadImageFiles(imageTitle, imageFiles);
	        model.addAttribute("imageUrl", imgurImageUrl);
		}
		
		if(imageUrl != null && !imageUrl.isEmpty()) {
	        model.addAttribute("imageUrl", imageUrl);
		}
		
		return "message_image_message_add";
	}
	
	private String uploadImageFiles(String imageTitle, MultipartFile[] imageFiles) {
		
		String imageUrl = "";
		
		if(imageFiles!=null && imageFiles.length > 0) {
			for (MultipartFile file : imageFiles) {
	            if (file.isEmpty()) {
	                continue; //繼續下一個檔案
	            }
	            byte[] bytes;
				try {
					bytes = file.getBytes();
			    	String clientId = "ce2eb2637b4abc0"; //設定自己的 Clinet ID
//		            Path path = Paths.get("/Users/9suser/Downloads/image002.jpg");
		            byte[] encoded = Base64.getEncoder().encode(bytes);
		            ImageUploadUtil imageUploadUtil = new ImageUploadUtil();
		            imageUrl = imageUploadUtil.imgurUploadToImgur(clientId, imageTitle, new String(encoded));
		            return imageUrl;
		            
		         
//		            Files.write(path, bytes);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		}
		
		
		return imageUrl;
	}
	@GetMapping("/newPlaceMessage")
	public String addPlaceMessagePage(Model model, String lineId) {
		System.out.println("LineId : " + lineId);
		model.addAttribute("lineId", lineId);

		return "message_place_message_add";
	}
	
	@PostMapping("/sendPlaceMessage")
	public String sendPlaceMessagePage(Model model, String lineId, String textMessage) {
		
		System.out.println("sendPlaceMessagePage lineId : " + lineId);

		double latitude = 25.05257506700204;
		double longitude = 121.54403119542826;
		
		if(!lineId.isEmpty()) {
			LineBotVO lineBotVO = lineBotService.getLineBotByMemberLineId(lineId);
			if(lineBotVO != null) {
				getGoogleMapNearbyPlace(latitude, longitude, lineId, lineBotVO.getToken());
			}
		}
		
		return "message_add";
	}
	
	String googleApiKey = "AIzaSyByFgt8sArSD4O65Nk3Cb_B9up6CntKmuQ";

	private void getGoogleMapNearbyPlace(double latitude, double longitude, String lineId, String token) {
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
		baseSkill.sendPushMessage(lineId, placeMessage, token);
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
