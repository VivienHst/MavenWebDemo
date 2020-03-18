package dev.vivienhuang.mavenwebdemo.service.line_member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.vivienhuang.mavenwebdemo.dao.line_member.ILineMemberFavoritePlaceDAO;
import dev.vivienhuang.mavenwebdemo.entity.member.LineMemberFavoritePlaceVO;

@Service
public class LineMemberFavoritePlaceService implements ILineMemberFavoritePlaceService {

	@Autowired
	ILineMemberFavoritePlaceDAO lineMemberFavoritePlaceDAO;
	
	@Override
	@Transactional
	public void createLineMemberFavoritePlace(LineMemberFavoritePlaceVO lineMemberFavoritePlaceVO) {
		lineMemberFavoritePlaceDAO.createLineMemberFavoritePlace(lineMemberFavoritePlaceVO);
	}

	@Override
	@Transactional
	public LineMemberFavoritePlaceVO getLineMemberFavoritePlace(String lineId, String placeId) {
		return lineMemberFavoritePlaceDAO.getLineMemberFavoritePlace(lineId, placeId);
	}

	@Override
	@Transactional
	public void updateLineMemberFavoritePlace(LineMemberFavoritePlaceVO lineMemberFavoritePlaceVO) {	
		lineMemberFavoritePlaceDAO.updateLineMemberFavoritePlace(lineMemberFavoritePlaceVO);
	}

	@Override
	@Transactional
	public void deleteLineMemberFavoritePlace(String lineId, String placeId) {
		lineMemberFavoritePlaceDAO.deleteLineMemberFavoritePlace(lineId, placeId);
	}

	@Override
	@Transactional
	public List<LineMemberFavoritePlaceVO> getLineMemberFavoritePlacesByLineId(String lineId) {
		return lineMemberFavoritePlaceDAO.getLineMemberFavoritePlacesByLineId(lineId);
	}

	@Override
	@Transactional
	public List<LineMemberFavoritePlaceVO> getLineMemberFavoritePlacesByLineId(String lineId, double latitude,
			double longitude) {
		return lineMemberFavoritePlaceDAO.getLineMemberFavoritePlacesByLineId(lineId, latitude, longitude);
	}

}
