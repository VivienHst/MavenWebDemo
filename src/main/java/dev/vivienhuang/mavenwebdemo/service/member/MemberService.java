package dev.vivienhuang.mavenwebdemo.service.member;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.vivienhuang.mavenwebdemo.dao.member.IMemberDAO;
import dev.vivienhuang.mavenwebdemo.entity.MemberVO;

@Service
public class MemberService implements IMemberService {

	@Autowired
	IMemberDAO memberDAO;
	
	@Override
	@Transactional
	public void createMember(MemberVO memberVO) {
		memberDAO.createMember(memberVO);
	}

	@Override
	@Transactional
	public List<MemberVO> getMembers() {
		return memberDAO.getMembers();
	}

	@Override
	@Transactional
	public MemberVO getMember(int id) {
		return memberDAO.getMember(id);
	}

	@Override
	@Transactional
	public void updateMember(MemberVO memberVO) {
		memberDAO.updateMember(memberVO);
	}

	@Override
	@Transactional
	public void deleteMember(int id) {
		memberDAO.deleteMember(id);
	}

}
