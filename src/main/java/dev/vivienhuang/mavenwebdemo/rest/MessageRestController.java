package dev.vivienhuang.mavenwebdemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.vivienhuang.mavenwebdemo.entity.LineWebhookLogVO;
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

		lineWebhookLogService.createLineWebhookLog(
				new LineWebhookLogVO(
						message.getDestination(),
						jsonInString, 
						new java.sql.Timestamp(System.currentTimeMillis()))
				);
        return message.getDestination();
    }

}
