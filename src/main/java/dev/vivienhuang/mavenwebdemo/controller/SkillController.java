package dev.vivienhuang.mavenwebdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import dev.vivienhuang.mavenwebdemo.entity.SkillVO;
import dev.vivienhuang.mavenwebdemo.service.skill.ISkillService;

@Controller
public class SkillController {
	@Autowired
	ISkillService skillService;
	
	@GetMapping("/skill")
	public String getSkillListPage(Model model) {
		List<SkillVO> skillVOs = skillService.getSkills();
		model.addAttribute("skills", skillVOs);
		model.addAttribute("newSkill", new SkillVO());

		return "skill_list";
	}
	
	@PostMapping("/saveSkill")
	public String saveSkillAction(@ModelAttribute("newSkill")SkillVO model) {
		skillService.createSkill(model);
		return "redirect:/skill";
		
	}
	
	@GetMapping("/skillDetail")
	public String getSkillDetail(@RequestParam("skillId")int skillId, 
			Model model) {
		SkillVO skillVO = skillService.getSkill(skillId);
		model.addAttribute("skill", skillVO);
		return "skill_update";
	}
	
	@PostMapping("/skillUpdate")
	public String skillUpdateAction(@ModelAttribute("skill") SkillVO skillVO) {
		skillService.upadteSkill(skillVO);
		return "redirect:/skill";	
	}
	
	@GetMapping("/skillDelete")
	public String getSkillDelete(@RequestParam("skillId")int skillId, 
			Model model) {
		skillService.deleteSkill(skillId);
		return "redirect:/skill";
	}

}
