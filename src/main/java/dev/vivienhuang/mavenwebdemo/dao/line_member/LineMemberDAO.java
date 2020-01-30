package dev.vivienhuang.mavenwebdemo.dao.line_member;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.vivienhuang.mavenwebdemo.entity.LineMemberVO;

@Repository
public class LineMemberDAO implements ILineMemberDAO{

		@Autowired
		SessionFactory sessionFactory;

		@Override
		public void createLineMember(LineMemberVO lineMemberVO) {
			Session session = sessionFactory.getCurrentSession();
			if(session.get( LineMemberVO.class, lineMemberVO.getLineId()) == null) {
				session.save(lineMemberVO);
			} else {
				System.out.println("LineId has existed");
			}
			
		}

		@Override
		public LineMemberVO getLineMember(String lineId) {
			Session session = sessionFactory.getCurrentSession();
			LineMemberVO lineMemberVO = session.get(LineMemberVO.class, lineId);
			return lineMemberVO;
		}

		@Override
		public List<LineMemberVO> getLineMembers() {
			Session session = sessionFactory.getCurrentSession();
			Query<LineMemberVO> query = session.createQuery(
					"from LineMemberVO order by createDate", LineMemberVO.class);
			
			return query.getResultList();
		}

		@Override
		public void updateLineMember(LineMemberVO lineMemberVO) {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(lineMemberVO);
		}

		@Override
		public void deleteLineMember(String lineId) {
			Session session = sessionFactory.getCurrentSession();
			session.delete(session.get( LineMemberVO.class, lineId));
		}

		@Override
		public List<LineMemberVO> getLineMembersByBotId(int botId) {
			Session session = sessionFactory.getCurrentSession();
			System.out.println("botId : " + botId);
			StoredProcedureQuery storedProcedure 
				= session.createStoredProcedureQuery("LineMemberGetByBotId", LineMemberVO.class);
			// set parameters
			storedProcedure.registerStoredProcedureParameter("botId", Integer.class, ParameterMode.IN);		
			storedProcedure.setParameter("botId", botId);
			// execute SP
			storedProcedure.execute();
	        List<LineMemberVO> resultList = (List<LineMemberVO>) storedProcedure.getResultList();
	
			return resultList;
		}
}
