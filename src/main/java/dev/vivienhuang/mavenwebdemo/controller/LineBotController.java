package dev.vivienhuang.mavenwebdemo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;
import dev.vivienhuang.mavenwebdemo.entity.SkillVO;
import dev.vivienhuang.mavenwebdemo.entity.member.LineMemberVO;
import dev.vivienhuang.mavenwebdemo.entity.viewmodel.LineBotVM;
import dev.vivienhuang.mavenwebdemo.service.line_member.ILineMemberService;
import dev.vivienhuang.mavenwebdemo.service.linebot.ILineBotService;
import dev.vivienhuang.mavenwebdemo.service.skill.ISkillService;

@Controller
public class LineBotController {

	@Autowired
	ILineBotService lineBotService;
	
	@Autowired
	ILineMemberService lineMemberService;
	
	@Autowired
	ISkillService skillService;
	
	@GetMapping("/linebot")
	public String getLineBotPage(Model model) {
		model.addAttribute("lineBots", lineBotService.getLineBots());
//		model.addAttribute("linebot", new LineBotVO());
		return "linebot_list";
	}
	
	@GetMapping("/addLineBot")
	public String getAddLineBotPage(Model model) {
		model.addAttribute("linebot", new LineBotVO());
		return "linebot_add";
	}
	
	@PostMapping("/saveLineBot")
	public String saveChatKeywordAction
			(@Valid @ModelAttribute("linebot")LineBotVO model,
					BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()){
			return "linebot_add";
		}else {
			lineBotService.createLineBot(new LineBotVO(
					model.getToken(), 
					model.getSecret(),
					model.getDisplayName(), 
					model.getType(), 
					model.getBotUid(),
					new java.sql.Timestamp(System.currentTimeMillis())));
			return "redirect:/linebot";
		}		
		
	}
	
	@GetMapping("/linebotDetail")
	public String getLinebotDetailPage(@RequestParam("botId")int botId, 
			Model model) {
		
		LineBotVO lineBotVO = lineBotService.getLineBot(botId);
		List<SkillVO> skillList = skillService.getSkills();
		Set<SkillVO> skillSet = new HashSet<SkillVO>();
		for (SkillVO skillVO : skillList) {
			skillSet.add(skillVO);
		}
			
		for (SkillVO skillVO : lineBotVO.getSkills()) {
			skillSet.remove(skillVO);
		}
		
//		lineBotVO.setSkills(skillSet);
		
		
		LineBotVM lineBotVM = new LineBotVM();
		lineBotVM.setLineBotVO(lineBotVO);
		lineBotVM.setUnUsedSkills(skillSet);
		
		model.addAttribute("linebot", lineBotVM);
		
  		return "linebot_update";
	}
	
	@GetMapping("/linebotMembers")
	public String getLinebotMembersPage(@RequestParam("botId")int botId, 
			Model model) {
		
		LineBotVO lineBotVO = lineBotService.getLineBot(botId);
		model.addAttribute("linebot", lineBotVO);
		
		List<LineMemberVO> lineMemberVOs = new ArrayList<LineMemberVO>();
		lineMemberVOs = lineMemberService.getLineMembersByBotId(botId);
		model.addAttribute("linemembers", lineMemberVOs);
	
	
  		return "linebot_members";
	}
	
	@PostMapping("/linebotUpdate")
	public String updateLinebotAction(@ModelAttribute("linebot")LineBotVM model) {
		System.out.println("updateLinebotAction");
		
		Set<SkillVO> skillVOs = new HashSet<SkillVO>();
		
		if(model.getBotSkills() != null) {
			for (Integer skillId : model.getBotSkills()) {
				System.out.println("getSkillId:" + skillId);
				skillVOs.add(skillService.getSkill(skillId));			
			}
		}
		
		
//		
		LineBotVO lineBotVO = model.getLineBotVO();
		lineBotVO.setSkills(skillVOs);
		lineBotVO.setUpdateDate(new java.sql.Timestamp(System.currentTimeMillis()));
		lineBotService.updateLineBot(lineBotVO);
		return "redirect:/linebot";
	}

	
	@GetMapping("/linebotDelete")
	public String linebotDeleteAction(@RequestParam("botId")int botId, 
			Model model) {
		
		lineBotService.deleteLineBot(botId);
		return "redirect:/linebot";
		
	}
}
