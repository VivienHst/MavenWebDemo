package dev.vivienhuang.mavenwebdemo.controller;

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
	
	@GetMapping("/keyword")
	public String getKeywordPage(Model model) {
		System.out.println("\n chatKeyWordService.getChatKeyWords()" 
				+ chatKeyWordService.getChatKeyWords().get(0).toString());
		List<ChatKeyWordVO> chatKeyWordVOs = chatKeyWordService.getChatKeyWords();
		model.addAttribute("chatKeywords", chatKeyWordVOs);
		model.addAttribute("keyword", new ChatKeyWordVO());

		return "keyword_list";
	}
	
	@PostMapping("/saveChatKeyword")
	public String saveChatKeywordAction(@ModelAttribute("keyword")ChatKeyWordVO model) {
		chatKeyWordService.createChatKeyWord(new ChatKeyWordVO(model.getChatKey(), model.getChatValue(), 
		new java.sql.Timestamp(System.currentTimeMillis())));
		return "redirect:/keyword";
	}
	
	@GetMapping("/keywordDelete")
	public String keywordDeleteAction(@RequestParam("cId")int cId) {
		chatKeyWordService.deleteChatKeyWord(cId);
		return "redirect:/keyword";
	}
}
