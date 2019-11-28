package dev.vivienhuang.mavenwebdemo.dao.member;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.vivienhuang.mavenwebdemo.entity.PermissionVO;

@Repository
public class PermissionDAO implements IPermissionDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void createPermission(PermissionVO permissionVO) {
		Session session = sessionFactory.getCurrentSession();
		session.save(permissionVO);
	}

	@Override
	public List<PermissionVO> getPermissions() {
		Session session = sessionFactory.getCurrentSession();
		Query<PermissionVO> query 
			= session.createQuery(" from PermissionVO order by id",
					PermissionVO.class);
		return query.getResultList();
	}

	@Override
	public PermissionVO getPermission(int id) {
		Session session = sessionFactory.getCurrentSession();
		PermissionVO permissionVO = session.get(PermissionVO.class, id);
		return permissionVO;
	}

	@Override
	public void updatePermission(PermissionVO permissionVO) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(permissionVO);
			
	}

	@Override
	public void deletePermission(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(PermissionVO.class, id));
	}

}
