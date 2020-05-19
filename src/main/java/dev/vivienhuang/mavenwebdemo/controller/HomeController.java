package dev.vivienhuang.mavenwebdemo.controller;



import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dev.vivienhuang.mavenwebdemo.entity.MemberPermissionPK;
import dev.vivienhuang.mavenwebdemo.entity.MemberPermissionVO;
import dev.vivienhuang.mavenwebdemo.entity.MemberVO;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;
import dev.vivienhuang.mavenwebdemo.linebot.message.flex.BoxComponent;
import dev.vivienhuang.mavenwebdemo.linebot.message.flex.BubbleContent;
import dev.vivienhuang.mavenwebdemo.linebot.message.flex.FlexContent;
import dev.vivienhuang.mavenwebdemo.linebot.message.flex.FlexMessage;
import dev.vivienhuang.mavenwebdemo.linebot.message.flex.ImageComponent;
import dev.vivienhuang.mavenwebdemo.linebot.message.flex.TextComponent;
import dev.vivienhuang.mavenwebdemo.linebot.skill.IBaseSkill;
import dev.vivienhuang.mavenwebdemo.linebot.skill.IWeatherSkill;
import dev.vivienhuang.mavenwebdemo.service.line_member.ILineMemberFavoritePlaceService;
import dev.vivienhuang.mavenwebdemo.service.member.IMemberPermissionService;
import dev.vivienhuang.mavenwebdemo.service.member.IMemberService;

@Controller
public class HomeController {
	
	private final String UPLOAD_DIRECTORY = "/images";

	@Autowired
	IMemberService memberService;
	
	@Autowired
	IMemberPermissionService memberPermissionService;

	@Autowired 
	IWeatherSkill weatherSkill;
	
	@Autowired
	IBaseSkill baseSkill;
	
	@Autowired
	ILineMemberFavoritePlaceService lineMemberFavoritePlaceService;
	
	@GetMapping("/home")
	public String getHomePage() {

		
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		
		if(user instanceof User) {
			User principal = (User) user;
			System.out.println("name : " + principal.getUsername());
		}
		
		
		return "home";
	}
	
	String googleApiKey = "AIzaSyByFgt8sArSD4O65Nk3Cb_B9up6CntKmuQ";

	private void getGoogleMapNearbyPlace(double latitude, double longitude) {
		String googleMapPlaceApiUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" + 
				"?location=" + latitude + "," +  longitude +
				"&radius=80" + 
				"&types=restaurant " + 
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
	   
	    
	    for (int i = 0; i < resultCount; i++) {
			JSONObject resultObject =  jsonArray.getJSONObject(i);
			
			String name = resultObject.getString("name");
	    	System.out.println("name : " + name);
	    	
	    	double rating = resultObject.getDouble("rating");
	    	System.out.println("rating : " + rating);

	    	String vicinity = resultObject.getString("vicinity");
	    	System.out.println("vicinity : " + vicinity);

		    JSONArray photoArray = resultObject.getJSONArray("photos");

	    	if(photoArray.length() > 0) {
	    		String photo_reference = photoArray.getJSONObject(0).getString("photo_reference");
		    	System.out.println("photo_reference : " + photo_reference);
	    		String photoUrl = getMapPhotoByPhotoReferance(photo_reference);
	    		BubbleContent placeContent = createRestaurantBubble(name, rating, vicinity, photoUrl);
	    		
	    		FlexMessage flexMessage  = new FlexMessage();
	    		flexMessage.setAltText(name);
	    		flexMessage.setContents(placeContent);
	    		
	    		List<LineMessage> placeMessage = new ArrayList<LineMessage>();
	    		placeMessage.add(flexMessage);
//	    		baseSkill.sendPushMessage(userId, messages, channelAccessToken);
	    	}
		}
	}
	
	private BubbleContent createRestaurantBubble(String name, double rating, String vicinity, String photoUrl) {
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
	
	@PostMapping("/uploadImageFile")
	public String uploadImageFileAction() {

//	public String uploadImageFileAction(@RequestParam CommonsMultipartFile file, HttpSession session) {
//		ServletContext servletContext = session.getServletContext();
//		final String path = servletContext.getRealPath(UPLOAD_DIRECTORY);
//		final String fileName = file.getOriginalFilename();
//		System.out.println(path + "/" + fileName);
//		try {
//			byte[] bytes = file.getBytes();
//			BufferedOutputStream bufferedOutputStream;
//			bufferedOutputStream = new BufferedOutputStream(
//					new FileOutputStream(
//							new File(path + File.separator + fileName)));
//			bufferedOutputStream.write(bytes);
//			bufferedOutputStream.flush();
//			bufferedOutputStream.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return "redirect:/home";
	}

	
}
