package dev.vivienhuang.mavenwebdemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.vivienhuang.mavenwebdemo.entity.ChatKeyWordVO;
import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;
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
	
	@GetMapping("/chatKeyWordMap")
	public Map<String, String> getChatKeyWordMap(){
		Map<String, String> keyWordMap = new HashMap<>();
		for(ChatKeyWordVO chatKeyWord : chatKeyWordService.getChatKeyWords()) {
			keyWordMap.put(chatKeyWord.getChatKey(), chatKeyWord.getChatValue());
		}
		return keyWordMap;
	}
	
}