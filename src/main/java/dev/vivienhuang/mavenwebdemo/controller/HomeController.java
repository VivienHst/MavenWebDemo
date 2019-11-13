package dev.vivienhuang.mavenwebdemo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import dev.vivienhuang.mavenwebdemo.entity.MemberPermissionPK;
import dev.vivienhuang.mavenwebdemo.entity.MemberPermissionVO;
import dev.vivienhuang.mavenwebdemo.entity.MemberVO;
import dev.vivienhuang.mavenwebdemo.service.member.IMemberPermissionService;
import dev.vivienhuang.mavenwebdemo.service.member.IMemberService;

@Controller
public class HomeController {

	@Autowired
	IMemberService memberService;
	
	@Autowired
	IMemberPermissionService memberPermissionService;

	
	@GetMapping("/home")
	public String getHomePage() {
//		System.out.println("\n\nmemberService.getMembers()" + memberService.getMembers().get(0).toString());
//		chatKeyWordService.createChatKeyWord(new ChatKeyWordVO("HI", "你好", 
//				new java.sql.Timestamp(System.currentTimeMillis())));
//		MemberVO memberVO = new MemberVO("andy100", "{noop}andy123", "Andy", "A", "test@tets.com", "886", "123456879",
//				"1", "test", new java.sql.Timestamp(System.currentTimeMillis()));
//		memberService.createMember(memberVO);
//		memberPermissionService.createMemberPermission(new MemberPermissionVO(new MemberPermissionPK(memberVO.getAccount(), "ROLE_EMPLOYEE")));
		
		/*
		 * (String account, String password, String firstName, String lastName, String email, String phoneCode,
			String phoneNumber, String state, String type, Timestamp createDate)*/
		return "home";
	}
	
	
}
