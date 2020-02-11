package dev.vivienhuang.mavenwebdemo.dao.skill;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.vivienhuang.mavenwebdemo.entity.SkillVO;

@Repository
public class SkillDAO implements ISkillDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void createSkill(SkillVO skillVO) {
		Session session = sessionFactory.getCurrentSession();
		session.save(skillVO);
	}

	@Override
	public List<SkillVO> getSkills() {
		Session session = sessionFactory.getCurrentSession();
		Query<SkillVO> query = session.createQuery("from SkillVO order by SkillId", SkillVO.class);
		List<SkillVO> skillVOs = query.getResultList();
 		return skillVOs;
	}

	@Override
	public SkillVO getSkill(int skillId) {
		Session session = sessionFactory.getCurrentSession();
		SkillVO skillVO = session.get(SkillVO.class, skillId);
		return skillVO;
	}

	@Override
	public void upadteSkill(SkillVO skillVO) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(skillVO);
	}

	@Override
	public void deleteSkill(int skillId) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(SkillVO.class, skillId));
	}

	@Override
	public SkillVO getBotSkill(int botId) {
		// TODO Auto-generated method stub
		return null;
	}

}
