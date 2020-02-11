package dev.vivienhuang.mavenwebdemo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;
import dev.vivienhuang.mavenwebdemo.entity.SkillVO;
import dev.vivienhuang.mavenwebdemo.linebot.skill.IMessageSkill;
import dev.vivienhuang.mavenwebdemo.linebot.skill.MessageSkill;
import dev.vivienhuang.mavenwebdemo.service.linebot.ILineBotService;
import dev.vivienhuang.mavenwebdemo.util.image.ImageUploadUtil;

@Controller
public class MessageController {
//	@GetMapping("/message")
//	public String getMessagePage(Model model) {
//		return "message_add";
//	}
	@Autowired
	ILineBotService lineBotService;
	
	@GetMapping("/message")
	public String getOneMemberMessagePage(Model model, String lineId) {
		System.out.println("LineId : " + lineId);
		model.addAttribute("lineId", lineId);

		return "message_add";
	}
	
	@GetMapping("/newTextMessage")
	public String addTextMessagePage(Model model, String lineId) {
		System.out.println("LineId : " + lineId);
		model.addAttribute("lineId", lineId);

		return "message_text_message_add";
	}
	
	@GetMapping("/newImageMessage")
	public String addImageMessagePage(Model model, String lineId) {
		System.out.println("LineId : " + lineId);
		model.addAttribute("lineId", lineId);

		return "message_image_message_add";
	}
	
	@PostMapping("/sendTextMessage")
	public String sendTextMessagePage(Model model, String lineId, String textMessage) {
		
		System.out.println("lineId : " + lineId);
		System.out.println("textMessage : " + textMessage);

		if(!lineId.isEmpty() && !textMessage.isEmpty()) {
			LineBotVO lineBotVO = lineBotService.getLineBotByMemberLineId(lineId);
			if(lineBotVO != null) {
				IMessageSkill messageSkill = new MessageSkill();
				messageSkill.sendTextMessage(lineId, textMessage, lineBotVO.getToken());
			}
		}
		return "message_add";
	}
	
	@PostMapping("/sendImageMessage")
	public String sendImageMessage(String lineId, MultipartFile[] imageFiles, Model model) {
//		String imageTitle = model.
		System.out.println("lineId : " + lineId);
		
		if(!lineId.isEmpty() && !(imageFiles.length == 0)) {
			LineBotVO lineBotVO = lineBotService.getLineBotByMemberLineId(lineId);
			if(lineBotVO != null) {
				IMessageSkill messageSkill = new MessageSkill();
				String imageUrl = uploadImageFiles("image" + System.currentTimeMillis(), imageFiles);

				if(!imageUrl.isEmpty()) {
					messageSkill.sendImageMessage(lineId, imageUrl, lineBotVO.getToken());
				}
			}
		}
		return "message_add";
	}
	
	@PostMapping("/uploadImageMessage")
	public String uploadImageMessage(String imageTitle, MultipartFile[] imageFiles, Model model) {
//		String imageTitle = model.
		System.out.println("imageTitle : " + imageTitle);
		model.addAttribute("imageTitle", imageTitle);
		String imageUrl = uploadImageFiles(imageTitle, imageFiles);
        model.addAttribute("imageUrl", imageUrl);

		return "message_image_message_add";
	}
	
	private String uploadImageFiles(String imageTitle, MultipartFile[] imageFiles) {
		
		String imageUrl = "";
		
		if(imageFiles!=null && imageFiles.length > 0) {
			for (MultipartFile file : imageFiles) {
	            if (file.isEmpty()) {
	                continue; //繼續下一個檔案
	            }
	            byte[] bytes;
				try {
					bytes = file.getBytes();
			    	String clientId = "ce2eb2637b4abc0"; //設定自己的 Clinet ID
//		            Path path = Paths.get("/Users/9suser/Downloads/image002.jpg");
		            byte[] encoded = Base64.getEncoder().encode(bytes);
		            ImageUploadUtil imageUploadUtil = new ImageUploadUtil();
		            imageUrl = imageUploadUtil.imgurUploadToImgur(clientId, imageTitle, new String(encoded));
		            return imageUrl;
		            
		         
//		            Files.write(path, bytes);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		}
		
		
		return imageUrl;
	}
}
