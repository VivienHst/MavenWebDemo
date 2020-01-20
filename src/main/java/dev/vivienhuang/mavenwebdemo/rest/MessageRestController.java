package dev.vivienhuang.mavenwebdemo.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.LineEvent;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
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

import dev.vivienhuang.mavenwebdemo.entity.BasicDBMessageVO;
import dev.vivienhuang.mavenwebdemo.entity.LineWebhookLogVO;
import dev.vivienhuang.mavenwebdemo.linebot.message.MessageModel;
import dev.vivienhuang.mavenwebdemo.linebot.message.ReplyMessageModel;
import dev.vivienhuang.mavenwebdemo.linebot.webhook.EventModel;
import dev.vivienhuang.mavenwebdemo.linebot.webhook.WebhookModel;
import dev.vivienhuang.mavenwebdemo.service.chat.IChatKeyWordService;
import dev.vivienhuang.mavenwebdemo.service.log.ILineWebhookLogService;

@RestController
@RequestMapping("/api/message")
public class MessageRestController {
	@Autowired 
	ILineWebhookLogService lineWebhookLogService;
	
	@Autowired
	IChatKeyWordService chatKeyWordService;
	
	@PostMapping("/bot_message")
    public String test3(@RequestBody String message) {
		System.out.println("message : " + message);
        return message;
    }
	
	@PostMapping("/line_message")
    public String receiveAlMessage(HttpServletRequest request) {
		
		Map<String, String> returnValue = new HashMap<String, String>();
		
		Enumeration<String> headerNames = request.getHeaderNames();
		
		while(headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			returnValue.put(headerName, request.getHeader(headerName));
		}
		
		String body = "";
		try {
			body =  IOUtils.toString(request.getReader());
			returnValue.put("body", body);
			lineWebhookLogService.createLineWebhookLog(
					new LineWebhookLogVO(
							new JSONObject(body).getString("destination"),
							body, 
							new java.sql.Timestamp(System.currentTimeMillis()))
					);
			ObjectMapper objectMapper = new ObjectMapper();
			WebhookModel webhookModel = objectMapper.readValue(body, WebhookModel.class);
			
			
			for(EventModel lineEvent : webhookModel.getEvents()) { 	
				if(lineEvent.getType().equals("message")) {
					String replyMessage = lineEvent.getMessage().getText();

					BasicDBMessageVO basicDBMessageVO = chatKeyWordService.getKeyWord(replyMessage, lineEvent.getSource().getUserId());
					int code =  basicDBMessageVO.getCode();
					System.out.println("basicDBMessageVO.getCode(): " + basicDBMessageVO.getCode());
					
					if (code == 1) {
						replyMessage = basicDBMessageVO.getMessage();
					}
					List<MessageModel> messageModels = new ArrayList<>();
					messageModels.add(new MessageModel("text", replyMessage));
					sendReplyMessage(new ReplyMessageModel(lineEvent.getReplyToken(), messageModels));
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("message : " + returnValue.toString());
        return "ok";
    }
	
	@PostMapping("/line_message2")
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
			if(lineEvent.getType().equals("message")) {
				String replyMessage = lineEvent.getMessage().getText();

				BasicDBMessageVO basicDBMessageVO = chatKeyWordService.getKeyWord(replyMessage, lineEvent.getSource().getUserId());
				int code =  basicDBMessageVO.getCode();
				System.out.println("basicDBMessageVO.getCode(): " + basicDBMessageVO.getCode());
				
				if (code == 1) {
					replyMessage = basicDBMessageVO.getMessage();
				}
				List<MessageModel> messageModels = new ArrayList<>();
				messageModels.add(new MessageModel("text", replyMessage));
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