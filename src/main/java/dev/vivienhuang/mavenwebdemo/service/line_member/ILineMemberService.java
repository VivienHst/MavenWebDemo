package dev.vivienhuang.mavenwebdemo.service.line_member;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.member.LineMemberVO;

public interface ILineMemberService {
	public void createLineMember(LineMemberVO lineMemberVO);
	public LineMemberVO getLineMember(String lineId);
	public List<LineMemberVO> getLineMembers();
	public void updateLineMember(LineMemberVO lineMemberVO);
	public void deleteLineMember(String lineId);
	public List<LineMemberVO> getLineMembersByBotId(int botId);
}
