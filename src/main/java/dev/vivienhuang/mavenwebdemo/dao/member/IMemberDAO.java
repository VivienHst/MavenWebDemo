package dev.vivienhuang.mavenwebdemo.dao.member;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.MemberVO;

public interface IMemberDAO {
	public void createMember(MemberVO memberVO);
	public List<MemberVO> getMembers();
	public MemberVO getMember(int id);
	public void updateMember(MemberVO memberVO);
	public void deleteMember(int id);
}
