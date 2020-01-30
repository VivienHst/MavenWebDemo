package dev.vivienhuang.mavenwebdemo.dao.linebot;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;

@Repository
public class LineBotDAO implements ILineBotDAO{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void createLineBot(LineBotVO lineBotVO) {
		Session session = sessionFactory.getCurrentSession();
		session.save(lineBotVO);
		
	}
	
	@Override
	public LineBotVO getLineBot(String destination) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("destination : " + destination);
		StoredProcedureQuery storedProcedure 
			= session.createStoredProcedureQuery("LineBotGetByDestination", LineBotVO.class);
		// set parameters
		storedProcedure.registerStoredProcedureParameter("destination", String.class, ParameterMode.IN);		
		storedProcedure.setParameter("destination", destination);
		// execute SP
		storedProcedure.execute();
        List<LineBotVO> resultQuery = (List<LineBotVO>) storedProcedure.getResultList();
      
		return resultQuery.get(0);
	}

	@Override
	public LineBotVO getLineBot(int botId) {
		Session session = sessionFactory.getCurrentSession();
		LineBotVO lineBotVO = session.get(LineBotVO.class, botId);
		return lineBotVO;
	}

	@Override
	public List<LineBotVO> getLineBots() {
		Session session = sessionFactory.getCurrentSession();
		Query<LineBotVO> query = session.createQuery(
				"from LineBotVO order by botId", LineBotVO.class);
		List<LineBotVO> lineBotVOs = query.getResultList();
		return lineBotVOs;
	}

	@Override
	public void updateLineBot(LineBotVO lineBotVO) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(lineBotVO);
	}

	@Override
	public void deleteLineBot(int botId) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(LineBotVO.class, botId));
	}
}
