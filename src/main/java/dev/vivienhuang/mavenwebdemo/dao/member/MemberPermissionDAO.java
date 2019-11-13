package dev.vivienhuang.mavenwebdemo.dao.member;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.vivienhuang.mavenwebdemo.entity.MemberPermissionPK;
import dev.vivienhuang.mavenwebdemo.entity.MemberPermissionVO;

@Repository
public class MemberPermissionDAO implements IMemberPermissionDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void createMemberPermission(MemberPermissionVO memberPermissionVO) {
		Session session = sessionFactory.getCurrentSession();
		session.save(memberPermissionVO);
		
	}

	@Override
	public List<MemberPermissionVO> getMemberPermissions() {
		Session session = sessionFactory.getCurrentSession();
		Query<MemberPermissionVO> query = session.createQuery("from MemberPermissionVO order by account",
					MemberPermissionVO.class); 
		List<MemberPermissionVO> memberPermissionVOs = query.getResultList();
		return memberPermissionVOs;
	}

	@Override
	public List<MemberPermissionVO> getMemberPermissions(String account) {
		Session session = sessionFactory.getCurrentSession();
		Query<MemberPermissionVO> query = session.createQuery("from MemberPermissionVO where id.account = '" + account + "'",
					MemberPermissionVO.class); 
		List<MemberPermissionVO> memberPermissionVOs = query.getResultList();
		return memberPermissionVOs;
	}

	@Override
	public MemberPermissionVO getMemberPermission(MemberPermissionPK memberPermissionPK) {

		Session session = sessionFactory.getCurrentSession();
		MemberPermissionVO memberPermissionVO = session.get(MemberPermissionVO.class, memberPermissionPK);
		return memberPermissionVO;
	}

	@Override
	public void updateMemberPermission(MemberPermissionVO memberPermissionVO) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(memberPermissionVO);
		
	}

	@Override
	public void deleteMemberPermission(MemberPermissionPK memberPermissionPK) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(MemberPermissionVO.class, memberPermissionPK));
	}

}
