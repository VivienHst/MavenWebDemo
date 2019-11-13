package dev.vivienhuang.mavenwebdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.vivienhuang.mavenwebdemo.entity.MemberVO;
import dev.vivienhuang.mavenwebdemo.service.member.IMemberPermissionService;
import dev.vivienhuang.mavenwebdemo.service.member.IMemberService;

@Controller
public class MemberController {
	
	@Autowired
	IMemberService memberService;

	@Autowired 
	IMemberPermissionService memberPermissionService;
	
	@GetMapping("/member")
	public String getMemberList(Model model) {
		model.addAttribute("members", memberService.getMembers());
		return "member_list";
	}
	
	@GetMapping("/memberDetail")
	public String getMemberDetail(@RequestParam("uid")int uid, Model model) {
		MemberVO memberVO =  memberService.getMember(uid);
		model.addAttribute("member", memberVO);
		
		model.addAttribute("memberPermissions", memberPermissionService.getMemberPermissions(memberVO.getAccount()));
		return "member_update";
	}
	
}
