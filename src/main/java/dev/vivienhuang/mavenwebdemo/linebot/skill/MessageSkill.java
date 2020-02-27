package dev.vivienhuang.mavenwebdemo.linebot.skill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.vivienhuang.mavenwebdemo.entity.BasicDBMessageVO;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.ImageMessage;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.MessagePayload;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.TextMessage;
import dev.vivienhuang.mavenwebdemo.linebot.message.MessageModel;
import dev.vivienhuang.mavenwebdemo.linebot.message.ReplyMessageModel;
import dev.vivienhuang.mavenwebdemo.linebot.webhook.EventModel;
import dev.vivienhuang.mavenwebdemo.service.chat.IChatKeyWordService;

@Component
public class MessageSkill implements IMessageSkill {
	@Autowired
	IBaseSkill baseSkill;
	
	@Autowired
	IChatKeyWordService chatKeyWordService;

	@Override
	public boolean replyKeyWordMessage(EventModel lineEvent, String channelAccessToken) {
		if(baseSkill.isTextMessage(lineEvent)) {
			String receiveMessage = lineEvent.getMessage().getText();
			BasicDBMessageVO basicDBMessageVO = chatKeyWordService.getKeyWord(receiveMessage, lineEvent.getSource().getUserId());
			int code =  basicDBMessageVO.getCode();
			System.out.println("basicDBMessageVO.getCode(): " + basicDBMessageVO.getCode());
			
			if (code == 1) {
				String replyMessage = basicDBMessageVO.getMessage();
				List<LineMessage> messageModels = new ArrayList<>();
				messageModels.add(new MessageModel("text", replyMessage));
				baseSkill.sendReplyMessage(new ReplyMessageModel(lineEvent.getReplyToken(), messageModels), channelAccessToken);
				return true;
			} 
		}		
		return false;
	}


	@Override
	public boolean replyEchoMessage(EventModel lineEvent, String channelAccessToken, String lineName) {
		if(baseSkill.isTextMessage(lineEvent)) {
			String receiveMessage = lineEvent.getMessage().getText();
			List<LineMessage> messageModels = new ArrayList<>();
			messageModels.add(new MessageModel("text", lineName + " 說:"));
			messageModels.add(new MessageModel("text", receiveMessage));
			baseSkill.sendReplyMessage(new ReplyMessageModel(lineEvent.getReplyToken(), messageModels), channelAccessToken);
			return true;
		}		
		return false;
	}
}
