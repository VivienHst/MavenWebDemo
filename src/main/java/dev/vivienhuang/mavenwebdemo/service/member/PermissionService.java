package dev.vivienhuang.mavenwebdemo.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.vivienhuang.mavenwebdemo.dao.member.IPermissionDAO;
import dev.vivienhuang.mavenwebdemo.entity.PermissionVO;

@Service
public class PermissionService implements IPermissionService {
	@Autowired
	IPermissionDAO permissionDAO;
	
	
	@Override
	@Transactional
	public void createPermission(PermissionVO permissionVO) {
		permissionDAO.createPermission(permissionVO);
	}

	@Override
	@Transactional
	public List<PermissionVO> getPermissions() {
		return permissionDAO.getPermissions();
	}

	@Override
	@Transactional
	public PermissionVO getPermission(int id) {
		return permissionDAO.getPermission(id);
	}

	@Override
	@Transactional
	public void updatePermission(PermissionVO permissionVO) {
		permissionDAO.updatePermission(permissionVO);
	}

	@Override
	@Transactional
	public void deletePermission(int id) {
		permissionDAO.deletePermission(id);
	}

}
