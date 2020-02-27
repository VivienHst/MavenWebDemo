package dev.vivienhuang.mavenwebdemo.dao.line_member;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.vivienhuang.mavenwebdemo.entity.member.LineMemberFavoritePlacePK;
import dev.vivienhuang.mavenwebdemo.entity.member.LineMemberFavoritePlaceVO;

@Repository
public class LineMemberFavoritePlaceDAO implements ILineMemberFavoritePlaceDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void createLineMemberFavoritePlace(LineMemberFavoritePlaceVO lineMemberFavoritePlaceVO) {
		Session session = sessionFactory.getCurrentSession();
		if(session.get( LineMemberFavoritePlaceVO.class, lineMemberFavoritePlaceVO.getLineMemberFavoritePlacePK()) == null) {
			session.save(lineMemberFavoritePlaceVO);
		} else {
			System.out.println("LineMemberFavoritePlace has existed");
		}		
	}

	@Override
	public LineMemberFavoritePlaceVO getLineMemberFavoritePlace(String lineId, String placeId) {
		Session session = sessionFactory.getCurrentSession();
		LineMemberFavoritePlaceVO lineMemberFavoritePlaceVO = session.get(LineMemberFavoritePlaceVO.class, 
				new LineMemberFavoritePlacePK(lineId, placeId));
		return lineMemberFavoritePlaceVO;
	
	}

	@Override
	public void updateLineMemberFavoritePlace(LineMemberFavoritePlaceVO lineMemberFavoritePlaceVO) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(lineMemberFavoritePlaceVO);
	}

	@Override
	public void deleteLineMemberFavoritePlace(String lineId, String placeId) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(LineMemberFavoritePlaceVO.class, new LineMemberFavoritePlacePK(lineId, placeId)));
	}

	@Override
	public List<LineMemberFavoritePlaceVO> getLineMemberFavoritePlacesByLineId(String lineId) {
		Session session = sessionFactory.getCurrentSession();
		Query<LineMemberFavoritePlaceVO> query = session.createQuery("from LineMemberFavoritePlaceVO where id.lineId = '" + lineId + "'",
				LineMemberFavoritePlaceVO.class); 
		List<LineMemberFavoritePlaceVO> lineMemberFavoritePlaceVOs = query.getResultList();
		return lineMemberFavoritePlaceVOs;
	}
}
