package dev.vivienhuang.mavenwebdemo.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
		sendRestResponseTest(chat);
		return "Echo : " + chat;
	}
	
	@PostMapping("/charEcho2")
	public String getEcho2String(@RequestParam String chat){
		return "Echo2 : " + chat;
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
	    ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
	    System.out.println(response.getBody());
	}
	
	
	
	private void sendMessageToLineUser(String userId, String message) {
		TextMessage textMessage = new TextMessage(message);
		MessagePayload messagePayload = new MessagePayload();
		messagePayload.setTo(userId);
		
		List<LineMessage> messages = new ArrayList<LineMessage>();
		messages.add(textMessage);
		
		
		final String url = "https://api.line.me/v2/bot/message/push";

	    RestTemplate restTemplate = new RestTemplate();	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	    MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
//	    map.add("payload", messages);

	    HttpEntity<MultiValueMap<String, String>> request 
	    	= new HttpEntity<MultiValueMap<String, String>>(map, headers);
	    ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
	    System.out.println(response.getBody());
		
	}
	
	
	String textMessage = "{\n" + 
			"    \"type\": \"text\",\n" + 
			"    \"text\": \"Hello, world\"\n" + 
			"}";
	
	/*
	 * public static string PushTextMessage(string LineId, string TextMessage, string ChannelAccessToken)
        {
            if (TextMessage.Contains("\t")) 
            {
                TextMessage = TextMessage.Replace("\t", "\n");
            }


            Models.LineMessage.LineMessage lineMessage = new Models.LineMessage.LineMessage()
            {
                to = LineId,
                messages = new List<MessageObject>(){
                    new TextMessage(){
                        text = TextMessage
                    }
                }
            };

            string text = JsonConvert.SerializeObject(lineMessage);

            return PushMessageToLine(text, ChannelAccessToken);
        }*/
	
}