package dev.vivienhuang.mavenwebdemo.linebot.skill;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;
import dev.vivienhuang.mavenwebdemo.entity.LineMemberVO;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.ImageMessage;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.MessagePayload;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.TextMessage;
import dev.vivienhuang.mavenwebdemo.linebot.message.ReplyMessageModel;
import dev.vivienhuang.mavenwebdemo.linebot.webhook.EventModel;
import dev.vivienhuang.mavenwebdemo.service.line_member.ILineMemberService;

@Component
public class BaseSkill implements IBaseSkill {
	
	private final String PUSH_MESSAGE_URL = "https://api.line.me/v2/bot/message/push";
	private final String REPLY_MESSAGE_URL = "https://api.line.me/v2/bot/message/reply";


	@Override
	public boolean isBotExist(String destination) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LineBotVO getLineBotInfo(String destination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean dealMessage(EventModel lineEvent) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public void pushTextMessage(String userId, String message, String channelAccessToken) {
		TextMessage textMessage = new TextMessage(message);
		List<LineMessage> messages = new ArrayList<LineMessage>();
		messages.add(textMessage);
		sendPushMessage(userId, messages, channelAccessToken);
	}
	
	@Override
	public void pushImageMessage(String userId, String url, String channelAccessToken) {
		ImageMessage imageMessage = new ImageMessage( url, url);
		List<LineMessage> messages = new ArrayList<LineMessage>();
		messages.add(imageMessage);
		sendPushMessage(userId, messages, channelAccessToken);		
	}

	
	@Override
	public void sendPushMessage(String userId, List<LineMessage> messages, String channelAccessToken) {
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

	    ResponseEntity<String> response = restTemplate.postForEntity(PUSH_MESSAGE_URL, request , String.class );
	    System.out.println(response.getBody());
	}


	
	
	@Override
	public void sendReplyMessage(ReplyMessageModel replyMessageModel, String channelAccessToken) {

	    RestTemplate restTemplate = new RestTemplate();	    
	    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Authorization", String.format("%s %s", "Bearer", channelAccessToken));
	    ObjectMapper Obj = new ObjectMapper(); 
	    String jsonStr = "";
        try { 
            jsonStr = Obj.writeValueAsString(replyMessageModel); 
            // Displaying JSON String 
            System.out.println(jsonStr); 
        } catch (IOException e) { 
            System.out.println(e.getMessage()); 
            e.printStackTrace(); 
        } 
	  
	    HttpEntity<String> request = new HttpEntity<String>(jsonStr, headers);
	    System.out.println("request : " + request.toString());

	    ResponseEntity<String> response = restTemplate.postForEntity(REPLY_MESSAGE_URL, request , String.class );
	    System.out.println(response.getBody());
	}

}
