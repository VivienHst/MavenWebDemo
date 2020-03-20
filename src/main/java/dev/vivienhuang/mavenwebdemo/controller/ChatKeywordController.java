package dev.vivienhuang.mavenwebdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.vivienhuang.mavenwebdemo.entity.BasicDBMessageVO;
import dev.vivienhuang.mavenwebdemo.entity.ChatKeyWordVO;
import dev.vivienhuang.mavenwebdemo.service.chat.IChatKeyWordService;

@Controller
public class ChatKeywordController {
	@Autowired
	IChatKeyWordService chatKeyWordService;
	@GetMapping("/keyword")
	public String getKeywordPage(Model model) {
		List<ChatKeyWordVO> chatKeyWordVOs = chatKeyWordService.getChatKeyWords();
		model.addAttribute("chatKeywords", chatKeyWordVOs);
		model.addAttribute("keyword", new ChatKeyWordVO());
		return "keyword_list";
	}
	
	@PostMapping("/saveChatKeyword")
	public String saveChatKeywordAction(
			Model model,
			@Valid @ModelAttribute("keyword")ChatKeyWordVO chatKeyWordVO,
			BindingResult bindingResult ) {
        
        if(bindingResult.hasErrors()) {
        	List<ChatKeyWordVO> chatKeyWordVOs = chatKeyWordService.getChatKeyWords();
    		model.addAttribute("chatKeywords", chatKeyWordVOs);
    		return "keyword_list";
        } else {
        	chatKeyWordService.createChatKeyWord(new ChatKeyWordVO(
        			chatKeyWordVO.getChatKey(), 
        			chatKeyWordVO.getChatValue(), 
    				new java.sql.Timestamp(System.currentTimeMillis())));
    		return "redirect:/keyword";
        }

	}
	
	private List<String> toList(BindingResult bindingResult) {
        List<String> errors = new ArrayList<>(); 
        if(bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(err -> {
                errors.add(err.getDefaultMessage());
            });
        }
        return errors;
    }
	
	@GetMapping("/keywordDelete")
	public String keywordDeleteAction(@RequestParam("cId")int cId) {
		chatKeyWordService.deleteChatKeyWord(cId);
		return "redirect:/keyword";
	}
	
	@GetMapping("/keywordDetail")
	public String keywordDetailAction(@RequestParam("cId")int cId, 
			Model model) {
		model.addAttribute("keyword", chatKeyWordService.getChatKeyWord(cId));
		return "keyword_update";
	}
	
	@PostMapping("/keywordUpdate")
	public String keywordUpdateAction(@ModelAttribute("keyword")ChatKeyWordVO chatKeyWordVO) {
		System.out.println("chatKeyWordVO.getChatValue() : " + chatKeyWordVO.getChatValue());
		chatKeyWordService.updateChatKeyWord(chatKeyWordVO);
		return "redirect:/keyword";
	}

}
