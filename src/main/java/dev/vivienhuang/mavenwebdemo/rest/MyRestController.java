package dev.vivienhuang.mavenwebdemo.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


import javax.annotation.PostConstruct;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.cache.CacheManager;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.vivienhuang.mavenwebdemo.linebot.member.MemberStatus;
import dev.vivienhuang.mavenwebdemo.service.cache.ILineBotCacheService;
import dev.vivienhuang.mavenwebdemo.service.cache.ILineMemberCacheService;
import dev.vivienhuang.mavenwebdemo.service.linebot.ILineBotService;

@RestController
@RequestMapping("/api")
public class MyRestController {
	
	@Autowired
	ILineBotService lineBotService;
	
	@Autowired
	ILineBotCacheService lineBotCacheService;
	
	@Autowired
	ILineMemberCacheService lineMemberCacheService;
	
	@Autowired
	private CacheManager cacheManager; 
	
	
	private List<String> students;
	private final String UPLOAD_DIRECTORY = "/images/";

	@GetMapping("/queryMmeberStatus")
	public String getMemberCacheTest(String lineId){
		return lineMemberCacheService.getMemberStatus(lineId).toString();
	}
	
	@GetMapping("/updateMmeberStatus1")
	public String getMemberUpdateToWait(String lineId){
		return lineMemberCacheService.getMemberStatus(lineId, MemberStatus.WAIT_FAVORITE_PLACE).toString();
	}
	
	@GetMapping("/updateMmeberStatus2")
	public String getMemberUpdateToNormal(String lineId){
		return lineMemberCacheService.getMemberStatus(lineId, MemberStatus.NORMAL).toString();
	}
	
	
	@PostConstruct
	public void loadData() {
		students = new ArrayList<>();
		
		students.add("Andy");
		students.add("Cindy");
		students.add("Evans");
	}
	
	@PostMapping("/uploadData")
	public String submit(
		@RequestParam String name,
		ModelMap modelMap) {
	 
//	    modelMap.addAttribute("name", name);
		System.out.println("name : " + name);
	    modelMap.addAttribute("name", name);
//	  
	    return "name : " + name;
	}
	
	@GetMapping("/test")
	public String getTest(String testText){
		return lineBotService.getLineBot(testText).toString();
	}
	
	@GetMapping("/saveKeyword")
	public String saveCacheTest(String keyword){
//		return lineBotCacheService.query(keyword);
		return "test";

	}
	
	@GetMapping("/query")
	public String getCacheTest(String keyword){
		
//		String[] cacheNames = (String[]) cacheManager.getCacheNames().toArray();
		
		String value = "";
		ValueWrapper valueWrapper = cacheManager.getCache("testQueryReult").get(keyword);
		if(valueWrapper == null) {
			value = lineBotCacheService.query(keyword);
		}  else {
			value = valueWrapper.get().toString();
		}
		
		return value;
		
//		return "";
	}
	
	@GetMapping("/remove")
	public String removeCacheTest(String keyword){
		
//		String[] cacheNames = (String[]) cacheManager.getCacheNames().toArray();
		
		
	
//		Cache cache = cacheManager.getCache("testQueryReult");
//		
//		if(cache.get(keyword) != null) {
//			cache.evict(keyword);
//		}
//		
		lineBotCacheService.delete(keyword);
		return "ok";
		
//		return "";
	}
	
	
	@PostMapping("/students")
	public List<String> getPostStudents(){
		return students;
	}
	
	@PostMapping("/uploadFileWithAddtionalData")
	public String submit(
		@RequestParam MultipartFile file,
		ModelMap modelMap) {
	 
//	    modelMap.addAttribute("name", name);
	    modelMap.addAttribute("file", file);
//	    System.out.println("name : " + name);
	    System.out.println("file : " + file.getOriginalFilename());

	    if(file != null) {
	    	List<MultipartFile> files = new ArrayList<MultipartFile>();
		    files.add(file);
		    try {
			    saveUploadedFiles(files);
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }catch (Exception e) {
		    	e.printStackTrace();
		    }
	    }
	    return "file : " + file.getOriginalFilename();
	}
	
	 //將檔案儲存
    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; //繼續下一個檔案
            }
            byte[] bytes = file.getBytes();
            
            Path path = Paths.get("/Users/9suser/Downloads/image002.jpg");
            byte[] encoded = Base64.getEncoder().encode(bytes);
            imgurUpload(new String(encoded));
            Files.write(path, bytes);
        }
    }
    
    public String imgurUpload(String imageBase64){ //插入圖片	
    	System.out.println("Base64 encode : " + imageBase64);
      	String url = "https://api.imgur.com/3/image";

    	String mashapeKey = "33279a919aa16be9b45e3f12cef127ff0893f700"; //
      // 設定自己的 Mashape Key
    	String clientId = "ce2eb2637b4abc0"; //設定自己的 Clinet ID
    	String titleString = ""; //設定圖片的標題

    	RestTemplate restTemplate = new RestTemplate();	    
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	headers.add("Authorization", String.format("%s %s", "Client-ID", clientId));	  
      	MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
      	params.add("title", titleString);
      	params.add("image", imageBase64);

      	HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);

      	ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );

      	String responseBody = response.getBody();
	    System.out.println(responseBody);
	    
	    String imageUrl = "";
	    try { 
	    	JSONObject jsonObject = new JSONObject(responseBody);
		    if(jsonObject.getInt("status") == 200 && jsonObject.getBoolean("success")) {
		    	imageUrl = jsonObject.getJSONObject("data").getString("link");
		    }
	    } catch (JSONException e) {
	    	e.printStackTrace();
	    }
	    System.out.println("imageUrl : " + imageUrl);
	    return imageUrl;
    }
}
