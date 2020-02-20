package dev.vivienhuang.mavenwebdemo.linebot.skill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;
import dev.vivienhuang.mavenwebdemo.entity.SkillVO;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.ImageMessage;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.MessagePayload;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.TextMessage;
import dev.vivienhuang.mavenwebdemo.linebot.message.ReplyMessageModel;
import dev.vivienhuang.mavenwebdemo.linebot.message.action.ActionObject;
import dev.vivienhuang.mavenwebdemo.linebot.message.action.MessageAction;
import dev.vivienhuang.mavenwebdemo.linebot.message.action.PostbackAction;
import dev.vivienhuang.mavenwebdemo.linebot.message.template.ButtonsTemplate;
import dev.vivienhuang.mavenwebdemo.linebot.message.template.TemplateMessage;
import dev.vivienhuang.mavenwebdemo.linebot.webhook.EventModel;
import dev.vivienhuang.mavenwebdemo.service.line_member.ILineMemberService;
import dev.vivienhuang.mavenwebdemo.service.linebot.ILineBotService;
import dev.vivienhuang.mavenwebdemo.service.skill.ISkillService;

@Component
public class BaseSkill implements IBaseSkill {
	
	@Autowired
	ILineBotService lineBotService;
	
	@Autowired
	ILineMemberService lineMemberService;
	
	@Autowired
	ISkillService skillService;
	
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
	public boolean dealMessage(LineBotVO lineBotVO, EventModel lineEvent) {
		if(isGetSkillList(lineBotVO, lineEvent)) {
			return true;
		}
		
		if(isGetSkillDescript(lineBotVO, lineEvent)) {
			return true;
		}
		
		return false;
	}
	
	// 是否為取得技能列表指令
	private boolean isGetSkillList(LineBotVO lineBotVO, EventModel lineEvent) {
		if(lineEvent.getType().equals("message") && lineEvent.getMessage().getText().equals("指令")) {
			return replySkillList(lineBotVO, lineEvent);
		}
		return false;
	}
	
	// 回復機器人技能列表
	private boolean replySkillList(LineBotVO lineBotVO, EventModel lineEvent) {
		Set<SkillVO> botSkills = lineBotService.getLineBot(lineBotVO.getBotId()).getSkills();
		
		if(botSkills.size() == 0) {
			return  false;
		}
		
		List<ActionObject> actionObjects = new ArrayList<ActionObject>();

		for (SkillVO skillVO : botSkills) {
			switch (skillVO.getSkillId()){
 				// 加入關鍵字指令
		 		case 1: 
					PostbackAction keywordAction = new PostbackAction("關鍵字指令", "keywordSkill");
					actionObjects.add(keywordAction);
					break;
				// 加入天氣指令
		 		case 3:
		 			MessageAction weatherAction = new MessageAction("天氣指令", "天氣");
					actionObjects.add(weatherAction);
					break;
				default:
				break;
			}
		}

		if(actionObjects.size() == 0) {
			return  false;
		}
		
		ButtonsTemplate buttonsTemplates = new ButtonsTemplate();
		buttonsTemplates.setText("可用指令");
		buttonsTemplates.setActions(actionObjects);
		
		TemplateMessage templateMessage = new TemplateMessage();
		
		templateMessage.setAltText("查看可用指令");
		templateMessage.setTemplate(buttonsTemplates);
		
		List<LineMessage> messages = new ArrayList<LineMessage>();

		messages.add(templateMessage);
		
		sendReplyMessage(new ReplyMessageModel(lineEvent.getReplyToken(), messages), lineBotVO.getToken());
		
		return true;
	}
	
	// 是否取得技能是使用說明
	private boolean isGetSkillDescript(LineBotVO lineBotVO, EventModel lineEvent) {
		// TODO Auto-generated method stub
		
		if(isKeywordDescript(lineBotVO, lineEvent)) {
			return true;
		}
		return false;
	}


	
	private boolean isKeywordDescript(LineBotVO lineBotVO, EventModel lineEvent) {
		if(lineEvent.getType().equals("postback") && lineEvent.getPostback().getData().equals("keywordSkill")) {
			String description = "輸入關鍵字可獲得對應訊息";
			replyTextMessage(lineEvent.getReplyToken(), description, lineBotVO.getToken());
			return true;
		}
		return false;
	}
	
	@Override
	public void replyTextMessage(String replyToken, String message, String channelAccessToken) {
		List<LineMessage> lineMessages = new ArrayList<LineMessage>();
		lineMessages.add(new TextMessage(message));
		sendReplyMessage(new ReplyMessageModel(replyToken, lineMessages), channelAccessToken);		
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
	    Obj.setSerializationInclusion(Include.NON_NULL);

	    String jsonStr = "";
        try { 
            jsonStr = Obj.writeValueAsString(messagePayload); 
            // Displaying JSON String 
            System.out.println(jsonStr); 
        } catch (IOException e) { 
            System.out.println(e.getMessage()); 
//            e.printStackTrace(); 
            System.out.println("IOException"); 

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
	    Obj.setSerializationInclusion(Include.NON_NULL);

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
