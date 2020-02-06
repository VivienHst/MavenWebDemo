package dev.vivienhuang.mavenwebdemo.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.vivienhuang.mavenwebdemo.entity.ChatKeyWordVO;
import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.MessagePayload;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.TextMessage;
import dev.vivienhuang.mavenwebdemo.service.chat.IChatKeyWordService;
import dev.vivienhuang.mavenwebdemo.service.linebot.ILineBotService;

@RestController
@RequestMapping("/api/bot")
public class BotRestController {

	@Autowired
	ILineBotService lineBotService;
	
	@Autowired
	IChatKeyWordService chatKeyWordService;
	
	@GetMapping("/linebots")
	public List<LineBotVO> getLineBotList(){
		return lineBotService.getLineBots();
	}
	
	@GetMapping("/linebot/{botid}")
	public LineBotVO getLineBot(@PathVariable int botid){
		return lineBotService.getLineBot(botid);
	}
	
	@GetMapping("/chatKeyWords")
	public List<ChatKeyWordVO> getChatKeyWordList(){
		return chatKeyWordService.getChatKeyWords();
	}
	
	@PostMapping("/chatKeyWords")
	public List<ChatKeyWordVO> getPostChatKeyWordList(){
		return chatKeyWordService.getChatKeyWords();
	}
	
	@PostMapping("/charEcho")
	public String getEchoString(@RequestParam String chat){
		
		if(chat.equals("test")) {
			sendTestRequest();
		} else {
			sendMessageToLineUser(myLineId, chat, CHANNEL_ACCESS_TOKEN);
//			sendLineMessage2(chat, myLineId);

		}
		
		return "Echo : " + chat;
	}
	
	@PostMapping("/charEcho2")
	public String getEcho2String(@RequestParam String chat){
		return "Echo2 : " + chat;
	}
	
	private void sendTestRequest() {
		final String url = "https://ptsv2.com/t/vn1tr-1576490349";
	    RestTemplate restTemplate = new RestTemplate();	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();

	    HttpEntity<MultiValueMap<String, String>> request 
	    	= new HttpEntity<MultiValueMap<String, String>>(map, headers);
	    System.out.println("request : " + request.toString());
//	    ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
	    ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );

	    System.out.println(response.getBody());
	}
    
	private static void sendRestResponseTest(String chat)
	{
	    final String url = "http://localhost:8080/mavenwebdemo/api/bot/charEcho2";

	    RestTemplate restTemplate = new RestTemplate();	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    
	    MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
	    map.add("chat", chat);

	    HttpEntity<MultiValueMap<String, String>> request 
	    	= new HttpEntity<MultiValueMap<String, String>>(map, headers);
	    System.out.println("request : " + request.toString());
	    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
	    System.out.println(response.getBody());
	}
	
//	public static String CHANNEL_ACCESS_TOKEN = "";
//	// line帳號的Channel Secret，要到line developers後台取得
//    public static String ChannelSecret_Test = "";
//	
//    private String myLineId = "";
	
	public static String CHANNEL_ACCESS_TOKEN = "dlnJHCvUro2QJe/NhbuOtuHZGxR4" +
            "hGhHN32nAJ78TNSn8eExk5LAzZwDC28zw1doJZrkDP5dmPTen3cz5GsXlrG4b1R/DOE" +
            "IMWyReA5CrWuyUJvbpg2AveUwxAsOZPl5cq9PIjxfkHZfYIKsCkc66gdB04t89/1O/w1cDnyilFU=";
	// line帳號的Channel Secret，要到line developers後台取得
    public static String ChannelSecret_Test = "eb65857006384e8845aa72f6ef5cdefa";
	
    private String myLineId = "U21428619758440bf95597dd80152a808";

    
	private void sendMessageToLineUser(String userId, String message, String channelAccessToken) {
		TextMessage textMessage = new TextMessage(message);
		MessagePayload messagePayload = new MessagePayload();
		messagePayload.setTo(userId);
		
		List<LineMessage> messages = new ArrayList<LineMessage>();
		messages.add(textMessage);
		messagePayload.setMessages(messages);
		final String url = "https://api.line.me/v2/bot/message/push";

	    RestTemplate restTemplate = new RestTemplate();	    
	    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Authorization", String.format("%s %s", "Bearer", channelAccessToken));
//	    MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
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
	
	String textMessage = "{\n" + 
			"    \"type\": \"text\",\n" + 
			"    \"text\": \"Hello, world\"\n" + 
			"}";
	
}