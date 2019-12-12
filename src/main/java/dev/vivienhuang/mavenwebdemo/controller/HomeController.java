package dev.vivienhuang.mavenwebdemo.controller;



import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dev.vivienhuang.mavenwebdemo.entity.MemberPermissionPK;
import dev.vivienhuang.mavenwebdemo.entity.MemberPermissionVO;
import dev.vivienhuang.mavenwebdemo.entity.MemberVO;
import dev.vivienhuang.mavenwebdemo.service.member.IMemberPermissionService;
import dev.vivienhuang.mavenwebdemo.service.member.IMemberService;

@Controller
public class HomeController {
	
	private final String UPLOAD_DIRECTORY = "/images";

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
	
	@PostMapping("/uploadImageFile")
	public String uploadImageFileAction() {

//	public String uploadImageFileAction(@RequestParam CommonsMultipartFile file, HttpSession session) {
//		ServletContext servletContext = session.getServletContext();
//		final String path = servletContext.getRealPath(UPLOAD_DIRECTORY);
//		final String fileName = file.getOriginalFilename();
//		System.out.println(path + "/" + fileName);
//		try {
//			byte[] bytes = file.getBytes();
//			BufferedOutputStream bufferedOutputStream;
//			bufferedOutputStream = new BufferedOutputStream(
//					new FileOutputStream(
//							new File(path + File.separator + fileName)));
//			bufferedOutputStream.write(bytes);
//			bufferedOutputStream.flush();
//			bufferedOutputStream.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return "redirect:/home";
	}

	
}
