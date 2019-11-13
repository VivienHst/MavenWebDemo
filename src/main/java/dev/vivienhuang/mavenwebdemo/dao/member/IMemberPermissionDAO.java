package dev.vivienhuang.mavenwebdemo.dao.member;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.MemberPermissionPK;
import dev.vivienhuang.mavenwebdemo.entity.MemberPermissionVO;

public interface IMemberPermissionDAO {
	public void createMemberPermission(MemberPermissionVO memberPermissionVO);
	public List<MemberPermissionVO> getMemberPermissions();
	public List<MemberPermissionVO> getMemberPermissions(String account);
	public MemberPermissionVO getMemberPermission(MemberPermissionPK memberPermissionPK);
	public void updateMemberPermission(MemberPermissionVO memberPermissionVO);
	public void deleteMemberPermission(MemberPermissionPK memberPermissionPK);
}
