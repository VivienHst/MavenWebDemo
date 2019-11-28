package dev.vivienhuang.mavenwebdemo.dao.member;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.PermissionVO;

public interface IPermissionDAO {
	public void createPermission(PermissionVO permissionVO);
	public List<PermissionVO> getPermissions();
	public PermissionVO getPermission(int id);
	public void updatePermission(PermissionVO permissionVO);
	public void deletePermission(int id);
}
