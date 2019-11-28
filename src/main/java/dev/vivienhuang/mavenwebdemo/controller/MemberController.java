package dev.vivienhuang.mavenwebdemo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.vivienhuang.mavenwebdemo.entity.MemberPermissionPK;
import dev.vivienhuang.mavenwebdemo.entity.MemberPermissionVO;
import dev.vivienhuang.mavenwebdemo.entity.MemberVO;
import dev.vivienhuang.mavenwebdemo.entity.PermissionVO;
import dev.vivienhuang.mavenwebdemo.service.member.IMemberPermissionService;
import dev.vivienhuang.mavenwebdemo.service.member.IMemberService;
import dev.vivienhuang.mavenwebdemo.service.member.IPermissionService;

@Controller
public class MemberController {
	
	@Autowired
	IMemberService memberService;

	@Autowired 
	IMemberPermissionService memberPermissionService;

	@Autowired
	IPermissionService permissionService;
	
	@GetMapping("/member")
	public String getMemberList(Model model) {
		model.addAttribute("members", memberService.getMembers());
		return "member_list";
	}
	
	@GetMapping("/memberDetail")
	public String getMemberDetail(@RequestParam("uid")int uid, Model model) {
		MemberVO memberVO =  memberService.getMember(uid);
		model.addAttribute("member", memberVO);
		model.addAttribute("newMemberPermission", new MemberPermissionPK(memberVO.getAccount()));
		
		List<MemberPermissionVO> memberPermissionVOs 
			= memberPermissionService.getMemberPermissions(memberVO.getAccount());
		List<PermissionVO> permissionVOs = permissionService.getPermissions();
		model.addAttribute("memberPermissions", memberPermissionVOs);
		List<String> permissionStrs = new ArrayList<>();
		for(PermissionVO permissionVO : permissionVOs) {
			boolean isHadPermission = false;
			for(MemberPermissionVO memberPermissionVO: memberPermissionVOs) {
				if(memberPermissionVO.getMemberPermissionPK().getPermission().equals(permissionVO.getPermission())) {
					isHadPermission = true;
					continue;
				}
			}
			
			if(!isHadPermission) {
				permissionStrs.add(permissionVO.getPermission());
			}
		}
		
		model.addAttribute("permissionList", permissionStrs);

		return "member_update";
	}
	
	@GetMapping("/addMember")
	public String getAddMember(Model model) {
		model.addAttribute("newMember", new MemberVO());
		
		return "member_add";
	}
	
	@PostMapping("/saveMember")
	public String saveMemberAction(@ModelAttribute("newMember") MemberVO memberVO) {
		memberVO.setCreateDate(new java.sql.Timestamp(System.currentTimeMillis()));
		memberVO.setPassword("{noop}" + memberVO.getPassword());
		memberVO.setState("1");
		memberVO.setType("test");
		memberService.createMember(memberVO);
		memberPermissionService.createMemberPermission(new MemberPermissionVO(new MemberPermissionPK(memberVO.getAccount(), "ROLE_EMPLOYEE")));
		return "redirect:/member";

	}	
	
	@PostMapping("/memberUpdate")
	public String memberUpdateAction(@ModelAttribute("member") MemberVO memberVO) {
		
		System.out.println("memberUpdate member : " + memberVO.toString());
		memberVO.setUpdateDate(new java.sql.Timestamp(System.currentTimeMillis()));
		memberService.updateMember(memberVO);
		return "redirect:/member";

	}	
	
	//memberDelete
	
	@GetMapping("/memberDelete")
	public String memberDeleteAction(@RequestParam("uid")int uid) {
		memberService.deleteMember(uid);
		return "redirect:/member";
	}
	
	@PostMapping("memberPermissionAdd")
	public String memberPermissionAddAction(@ModelAttribute("newMemberPermission") MemberPermissionPK newMemberPermission){
		memberPermissionService.createMemberPermission(new MemberPermissionVO(newMemberPermission));
		return "redirect:/member";
	}
}
