package dev.vivienhuang.mavenwebdemo.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.vivienhuang.mavenwebdemo.entity.LineWebhookLogVO;
import dev.vivienhuang.mavenwebdemo.linebot.message.MessageModel;
import dev.vivienhuang.mavenwebdemo.linebot.message.ReplyMessageModel;
import dev.vivienhuang.mavenwebdemo.linebot.webhook.EventModel;
import dev.vivienhuang.mavenwebdemo.linebot.webhook.WebhookModel;
import dev.vivienhuang.mavenwebdemo.service.log.ILineWebhookLogService;

@RestController
@RequestMapping("/api/message")
public class MessageRestController {
	@Autowired 
	ILineWebhookLogService lineWebhookLogService;
	
	@PostMapping("/bot_message")
    public String test3(@RequestBody String message) {
		System.out.println("message : " + message);
        return message;
    }
	
	@PostMapping("/line_message")
    public String receiveLineMessage(@RequestBody WebhookModel message) {
		ObjectMapper mapper = new ObjectMapper();
		//Object to JSON in String
		String jsonInString = "";
		try {
			jsonInString = mapper.writeValueAsString(message);
			System.out.println("message : " + jsonInString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try{
			lineWebhookLogService.createLineWebhookLog(
					new LineWebhookLogVO(
							message.getDestination(),
							jsonInString, 
							new java.sql.Timestamp(System.currentTimeMillis()))
					);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		for(EventModel lineEvent : message.getEvents()) {
			/*
			 * {
			    "destination": "U1891f48c88a0643a59fa39eb3909be1e",
			    "events": [
			        {
			            "replyToken": "ea52a4d2f8c4432c9194cb7bbf663006",
			            "type": "message",
			            "mode": "active",
			            "timestamp": "1579192167128",
			            "source": {
			                "type": "user",
			                "userid": null,
			                "groupId": null,
			                "roomId": null
			            }
			        }
			    ]
			}*/
			
			if(lineEvent.getType().equals("message")) {
				List<MessageModel> messageModels = new ArrayList<>();
				messageModels.add(new MessageModel("text", lineEvent.getMessage().getText()));
				sendReplyMessage(new ReplyMessageModel(lineEvent.getReplyToken(), messageModels));
			}
			
		}
		
		
        return "ok";
    }
	
	private final String CHANNEL_ACCESS_TOKEN = "La6ZIBBddbqkpClpzIokmIwia16jTrtpum/"
			+ "YRtN58DaPUEsqrfLQYIBmAhLmNyrJBXXS+MKiXQix6Du9XjO+xAd/uvhiiU+bb7CdRoSw"
			+ "OPww0/E12BzLcawJgmJ8Q3aCcv2LgD8SsT/dlM8ArUDFxAdB04t89/1O/w1cDnyilFU=";
	
	private final String REPLY_MESSAGE_URL = "https://api.line.me/v2/bot/message/reply";
	
	private void sendReplyMessage(ReplyMessageModel replyMessageModel) {

	    RestTemplate restTemplate = new RestTemplate();	    
	    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Authorization", String.format("%s %s", "Bearer", CHANNEL_ACCESS_TOKEN));
//	    MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
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

	    ResponseEntity<String> response = restTemplate.postForEntity( REPLY_MESSAGE_URL, request , String.class );
	    System.out.println(response.getBody());
	}

}
