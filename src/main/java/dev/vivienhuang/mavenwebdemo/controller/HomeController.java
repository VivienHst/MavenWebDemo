package dev.vivienhuang.mavenwebdemo.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.vivienhuang.mavenwebdemo.dao.chat.IChatKeyWordDAO;
import dev.vivienhuang.mavenwebdemo.entity.ChatKeyWordVO;
import dev.vivienhuang.mavenwebdemo.entity.MemberVO;
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
//		System.out.println("\n\nmemberService.getMembers()" + memberService.getMembers().get(0).toString());
//		chatKeyWordService.createChatKeyWord(new ChatKeyWordVO("HI", "你好", 
//				new java.sql.Timestamp(System.currentTimeMillis())));
		
		memberService.createMember(new MemberVO("andy100", "{noop}andy123", "Andy", "A", "test@tets.com", "886", "123456879",
				"1", "test", new java.sql.Timestamp(System.currentTimeMillis())));
		
		/*
		 * (String account, String password, String firstName, String lastName, String email, String phoneCode,
			String phoneNumber, String state, String type, Timestamp createDate)*/
		return "home";
	}
	
	
}
