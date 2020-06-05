package dev.vivienhuang.mavenwebdemo.linebot.skill;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import dev.vivienhuang.mavenwebdemo.entity.BasicDBMessageVO;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.ImageMessage;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;
import dev.vivienhuang.mavenwebdemo.linebot.message.MessageModel;
import dev.vivienhuang.mavenwebdemo.linebot.message.ReplyMessageModel;
import dev.vivienhuang.mavenwebdemo.linebot.webhook.EventModel;

@Component
public class WeatherSkill implements IWeatherSkill {

	@Autowired
	IBaseSkill baseSkill;
	
	@Override
	public boolean replyWeatherSkill(EventModel lineEvent, String channelAccessToken) {
		if(baseSkill.isTextMessage(lineEvent)) {
			String receiveMessage = lineEvent.getMessage().getText();
			if(receiveMessage.equals("天氣")) {
				String replyMessage = getObserveRadarDataImage();
				ImageMessage imageMessage = new ImageMessage( replyMessage, replyMessage);
				List<LineMessage> messages = new ArrayList<LineMessage>();
				messages.add(imageMessage);
				baseSkill.sendReplyMessage(new ReplyMessageModel(lineEvent.getReplyToken(), messages), channelAccessToken);
				return true;
			}
			
		}	
		return false;
	}

	
	@Override
	public String getObserveRadarDataImage() {
	    String cwbUrl = "https://www.cwb.gov.tw/Data/js/obs_img/Observe_radar.js";
	    RestTemplate restTemplate = new RestTemplate();	 
	    ResponseEntity<String> result = restTemplate.getForEntity(cwbUrl, String.class);
	    
	    String radarData;
	    
	    if(result.getStatusCodeValue() == 200) {
	    	radarData =  result.getBody();
	    	String[] imageRaw = radarData.split("img\":'");
	    	String imageUrl = imageRaw[1].substring(0, imageRaw[1].indexOf("',"));
		    String radarDataImageUrl = "https://www.cwb.gov.tw/Data/radar/" + imageUrl;
		    System.out.println("radarDataImageUrl : " + radarDataImageUrl);
	    	return radarDataImageUrl;
	    }
	    return "";

	}
}
