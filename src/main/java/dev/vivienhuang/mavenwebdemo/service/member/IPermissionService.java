package dev.vivienhuang.mavenwebdemo.service.member;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.PermissionVO;
public interface IPermissionService {
	public void createPermission(PermissionVO permissionVO);
	public List<PermissionVO> getPermissions();
	public PermissionVO getPermission(int id);
	public void updatePermission(PermissionVO permissionVO);
	public void deletePermission(int id);
}

