package dev.vivienhuang.mavenwebdemo.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.vivienhuang.mavenwebdemo.dao.member.IMemberPermissionDAO;
import dev.vivienhuang.mavenwebdemo.entity.MemberPermissionPK;
import dev.vivienhuang.mavenwebdemo.entity.MemberPermissionVO;

@Service
public class MemberPermissionService implements IMemberPermissionService {

	@Autowired
	IMemberPermissionDAO memberPermissionDAO;
	
	@Override
	@Transactional
	public void createMemberPermission(MemberPermissionVO memberPermissionVO) {
		memberPermissionDAO.createMemberPermission(memberPermissionVO);
	}

	@Override
	@Transactional
	public List<MemberPermissionVO> getMemberPermissions() {
		return memberPermissionDAO.getMemberPermissions();
	}

	@Override
	@Transactional
	public List<MemberPermissionVO> getMemberPermissions(String account) {
		return memberPermissionDAO.getMemberPermissions(account);
	}

	@Override
	@Transactional
	public MemberPermissionVO getMemberPermission(MemberPermissionPK memberPermissionPK) {
		return memberPermissionDAO.getMemberPermission(memberPermissionPK);
	}

	@Override
	@Transactional
	public void updateMemberPermission(MemberPermissionVO memberPermissionVO) {
		memberPermissionDAO.updateMemberPermission(memberPermissionVO);
	}

	@Override
	@Transactional
	public void deleteMemberPermission(MemberPermissionPK memberPermissionPK) {
		memberPermissionDAO.deleteMemberPermission(memberPermissionPK);
	}

}
