package dev.vivienhuang.mavenwebdemo.util.image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

public class ImageUploadUtil {
	
	private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; //繼續下一個檔案
            }
            byte[] bytes = file.getBytes();
            
            Path path = Paths.get("/Users/9suser/Downloads/image002.jpg");
            byte[] encoded = Base64.getEncoder().encode(bytes);
//            imgurUploadToImgur(new String(encoded));
            Files.write(path, bytes);
        }
    }
	
	//上傳圖片到imgur, 回傳圖片網址
	public String imgurUploadToImgur(String clientId, String titleString, String imageBase64){ //插入圖片	
      	String url = "https://api.imgur.com/3/image";
      // String mashapeKey = "33279a919aa16be9b45e3f12cef127ff0893f700"; //
      // 設定自己的 Mashape Key
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
