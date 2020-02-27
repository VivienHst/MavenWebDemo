package dev.vivienhuang.mavenwebdemo.linebot.skill;

import java.nio.charset.StandardCharsets;

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

import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;
import dev.vivienhuang.mavenwebdemo.entity.member.LineMemberVO;
import dev.vivienhuang.mavenwebdemo.linebot.webhook.EventModel;
import dev.vivienhuang.mavenwebdemo.service.line_member.ILineMemberService;

@Component
public class MemberSkill implements IMemberSkill {

	private final String GET_MEMBER_PROFILE_URL = "https://api.line.me/v2/bot/profile/";
	
	@Autowired
	ILineMemberService lineMemberService;
	
	@Override
	public LineMemberVO registerMember(LineBotVO lineBotVO, EventModel lineEvent) {
		JSONObject jsonObject = new JSONObject(getLineMemberProfile(lineEvent.getSource().getUserId(), lineBotVO.getToken()));
		LineMemberVO lineMemberVO  = new LineMemberVO();
		lineMemberVO.setLineId(jsonObject.getString("userId"));
		lineMemberVO.setLineName(jsonObject.getString("displayName"));
		lineMemberVO.setLinePicture(jsonObject.getString("pictureUrl"));
		lineMemberVO.setBotId(lineBotVO.getBotId());
		lineMemberVO.setCreateDate(new java.sql.Timestamp(System.currentTimeMillis()));
		lineMemberService.createLineMember(lineMemberVO);		
		return lineMemberVO;
	}
	
	@Override
	public String getLineMemberProfile(String lineId, String channelAccessToken) {
		String url = GET_MEMBER_PROFILE_URL + lineId;
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());	    
		restTemplate.getMessageConverters().set(1, 
				new StringHttpMessageConverter(StandardCharsets.UTF_8));
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Authorization", String.format("%s %s", "Bearer", channelAccessToken));
		
	    HttpEntity<String> request = new HttpEntity<String>(headers);
	    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request , String.class );
//	    System.out.println(response.getBody());
	    System.out.println("response.getBody() : " + response.getBody());

	    return  response.getBody();
	}

}
