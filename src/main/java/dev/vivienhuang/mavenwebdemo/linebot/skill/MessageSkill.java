package dev.vivienhuang.mavenwebdemo.linebot.skill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.MessagePayload;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.TextMessage;

public class MessageSkill implements IMessageSkill {
	private final String url = "https://api.line.me/v2/bot/message/push";
	
	@Override
	public void sendTextMessage(String userId, String message, String channelAccessToken) {
		TextMessage textMessage = new TextMessage(message);
		List<LineMessage> messages = new ArrayList<LineMessage>();
		messages.add(textMessage);
		sendLineMessage(userId, messages, channelAccessToken);
	}

	
	@Override
	public void sendLineMessage(String userId, List<LineMessage> messages, String channelAccessToken) {
		MessagePayload messagePayload = new MessagePayload();
		messagePayload.setTo(userId);		
		messagePayload.setMessages(messages);

	    RestTemplate restTemplate = new RestTemplate();	    
	    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Authorization", String.format("%s %s", "Bearer", channelAccessToken));
	    ObjectMapper Obj = new ObjectMapper(); 
	    String jsonStr = "";
        try { 
            jsonStr = Obj.writeValueAsString(messagePayload); 
            // Displaying JSON String 
            System.out.println(jsonStr); 
        } catch (IOException e) { 
            System.out.println(e.getMessage()); 

            e.printStackTrace(); 
        } 
	  
	    HttpEntity<String> request = new HttpEntity<String>(jsonStr, headers);
	    System.out.println("request : " + request.toString());

	    ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
	    System.out.println(response.getBody());
	}

}
