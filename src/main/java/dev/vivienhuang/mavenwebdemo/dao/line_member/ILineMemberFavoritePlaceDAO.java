package dev.vivienhuang.mavenwebdemo.dao.line_member;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.member.LineMemberFavoritePlaceVO;

public interface ILineMemberFavoritePlaceDAO {
	public void createLineMemberFavoritePlace(LineMemberFavoritePlaceVO lineMemberFavoritePlaceVO);
	public LineMemberFavoritePlaceVO getLineMemberFavoritePlace(String lineId, String placeId);
	public void updateLineMemberFavoritePlace(LineMemberFavoritePlaceVO lineMemberFavoritePlaceVO);
	public void deleteLineMemberFavoritePlace(String lineId, String placeId);
	public List<LineMemberFavoritePlaceVO> getLineMemberFavoritePlacesByLineId(String lineId);
	public List<LineMemberFavoritePlaceVO> getLineMemberFavoritePlacesByLineId(String lineId, double latitude, double longitude);

}
