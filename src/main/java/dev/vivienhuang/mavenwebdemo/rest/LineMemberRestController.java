package dev.vivienhuang.mavenwebdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.vivienhuang.mavenwebdemo.entity.LineMemberVO;
import dev.vivienhuang.mavenwebdemo.service.line_member.ILineMemberService;

@RestController
@RequestMapping("/api/linemember")
public class LineMemberRestController {
	
	@Autowired
	ILineMemberService lineMemberService;
	
	@GetMapping("/add")
	public List<LineMemberVO> getLineBotList(){
		
		lineMemberService.createLineMember(new LineMemberVO(
				"testid",
				1,
				1,
				new java.sql.Timestamp(System.currentTimeMillis())));
		return lineMemberService.getLineMembers();
	}

}
