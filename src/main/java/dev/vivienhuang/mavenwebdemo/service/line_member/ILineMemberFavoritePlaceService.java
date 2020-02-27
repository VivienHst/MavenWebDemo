package dev.vivienhuang.mavenwebdemo.service.line_member;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.member.LineMemberFavoritePlaceVO;

public interface ILineMemberFavoritePlaceService {
	public void createLineMemberFavoritePlace(LineMemberFavoritePlaceVO lineMemberFavoritePlaceVO);
	public LineMemberFavoritePlaceVO getLineMemberFavoritePlace(String lineId, String placeId);
	public void updateLineMemberFavoritePlace(LineMemberFavoritePlaceVO lineMemberFavoritePlaceVO);
	public void deleteLineMemberFavoritePlace(String lineId, String placeId);
	public List<LineMemberFavoritePlaceVO> getLineMemberFavoritePlacesByLineId(String lineId);
}
