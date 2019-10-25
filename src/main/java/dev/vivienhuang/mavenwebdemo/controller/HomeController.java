package dev.vivienhuang.mavenwebdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import dev.vivienhuang.mavenwebdemo.dao.chat.IChatKeyWordDAO;
import dev.vivienhuang.mavenwebdemo.entity.ChatKeyWordVO;
import dev.vivienhuang.mavenwebdemo.service.chat.ChatKeyWordService;
import dev.vivienhuang.mavenwebdemo.service.chat.IChatKeyWordService;
import dev.vivienhuang.mavenwebdemo.service.member.IMemberService;

@Controller
public class HomeController {

	@Autowired
	IMemberService memberService;
	
	@Autowired
	IChatKeyWordService chatKeyWordService;
	
	@GetMapping("/home")
	public String getHomePage() {
		System.out.println("\n\nmemberService.getMembers()" + memberService.getMembers().get(0).toString());
//		chatKeyWordService.createChatKeyWord(new ChatKeyWordVO("HI", "你好", 
//				new java.sql.Timestamp(System.currentTimeMillis())));
		return "home";
	}
}
