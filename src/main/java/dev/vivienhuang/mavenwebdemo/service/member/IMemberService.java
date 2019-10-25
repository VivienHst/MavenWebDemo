package dev.vivienhuang.mavenwebdemo.service.member;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.MemberVO;

public interface IMemberService {
	public void createMember(MemberVO memberVO);
	public List<MemberVO> getMembers();
	public MemberVO getMember(int id);
	public void updateMember(MemberVO memberVO);
	public void deleteMember(int id);
}
