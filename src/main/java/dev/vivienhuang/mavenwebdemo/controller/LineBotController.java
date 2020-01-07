package dev.vivienhuang.mavenwebdemo.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;
import dev.vivienhuang.mavenwebdemo.entity.SkillVO;
import dev.vivienhuang.mavenwebdemo.service.linebot.ILineBotService;

@Controller
public class LineBotController {

	@Autowired
	ILineBotService lineBotService;
	
	@GetMapping("/linebot")
	public String getLineBotPage(Model model) {
		model.addAttribute("lineBots", lineBotService.getLineBots());
		model.addAttribute("linebot", new LineBotVO());
		return "linebot_list";
	}
	
	@PostMapping("/saveLineBot")
	public String saveChatKeywordAction(@ModelAttribute("linebot")LineBotVO model) {
		lineBotService.createLineBot(new LineBotVO(
				model.getToken(), 
				model.getSecret(),
				model.getDisplayName(), 
				model.getType(), 
				new java.sql.Timestamp(System.currentTimeMillis())));
		return "redirect:/linebot";
	}
	
	@GetMapping("/linebotDetail")
	public String getLinebotDetailPage(@RequestParam("botId")int botId, 
			Model model) {
		
		LineBotVO lineBotVO = lineBotService.getLineBot(botId);
		model.addAttribute("linebot", lineBotVO);
		
		
		for (SkillVO skillVO : lineBotVO.getSkills()) {
            System.out.print(skillVO.toString());
		}
  		return "linebot_update";
	}
	
	@PostMapping("/linebotUpdate")
	public String updateLinebotAction(@ModelAttribute("linebot")LineBotVO model) {
		model.setUpdateDate(new java.sql.Timestamp(System.currentTimeMillis()));
		lineBotService.updateLineBot(model);
		return "redirect:/linebot";
	}

	
	@GetMapping("/linebotDelete")
	public String linebotDeleteAction(@RequestParam("botId")int botId, 
			Model model) {
		
		lineBotService.deleteLineBot(botId);
		return "redirect:/linebot";
		
	}
}
