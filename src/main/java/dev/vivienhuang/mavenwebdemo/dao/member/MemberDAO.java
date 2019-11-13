package dev.vivienhuang.mavenwebdemo.dao.member;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import dev.vivienhuang.mavenwebdemo.entity.MemberVO;

@Repository
public class MemberDAO implements IMemberDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void createMember(MemberVO memberVO) {
		Session session = sessionFactory.getCurrentSession();
		session.save(memberVO);
		new User(memberVO.getAccount(), memberVO.getPassword(), 
				AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE"));
//		AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");
	}

	@Override
	public List<MemberVO> getMembers() {
		Session session = sessionFactory.getCurrentSession();
		Query<MemberVO> theQuery = session.createQuery("from MemberVO order by uid", MemberVO.class);
		List<MemberVO> memberVOs = theQuery.getResultList();
		return memberVOs;
	}

	@Override
	public MemberVO getMember(int id) {
		Session session = sessionFactory.getCurrentSession();
		MemberVO memberVO = session.get(MemberVO.class, id);		
		return memberVO;
	}

	@Override
	public void updateMember(MemberVO memberVO) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(memberVO);
	}

	@Override
	public void deleteMember(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(MemberVO.class, id));
	}

}
