package dev.vivienhuang.mavenwebdemo.entity.member;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="LineMemberFavoritePlace")
public class LineMemberFavoritePlaceVO {
	
	@EmbeddedId
	private LineMemberFavoritePlacePK lineMemberFavoritePlacePK;
	
//	@Column(name="LineId")
//	private String lineId;
//	
//	@Column(name="PlaceId")
//	private String placeId;
	
	@Column(name="PlaceName")
	private String placeName;
	
	@Column(name="Rating")
	private Double rating;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="PhotoUrl")
	private String photoUrl;
	
	@Column(name="MapUrl")
	private String mapUrl;
	
	@Column(name="Latitude")
	private Double latitude;
	
	@Column(name="Longitude")
	private Double longitude;
	
	@Column(name="Type")
	private Integer type;

	@Column(name="CreateDate")
	private Timestamp createDate;
	
	@Column(name="UpdateDate")
	private Timestamp updateDate;
	
	public LineMemberFavoritePlaceVO() {
		super();
	}

	public LineMemberFavoritePlaceVO(String lineId, String placeId) {
		super();
		this.lineMemberFavoritePlacePK = new LineMemberFavoritePlacePK(lineId, placeId);
	}
	
	public LineMemberFavoritePlaceVO(String lineId, String placeId, String placeName, Double rating, String address,
			String photoUrl, String mapUrl, Double latitude, Double longitude) {
		super();
		this.lineMemberFavoritePlacePK = new LineMemberFavoritePlacePK(lineId, placeId);
		this.placeName = placeName;
		this.rating = rating;
		this.address = address;
		this.photoUrl = photoUrl;
		this.mapUrl = mapUrl;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public LineMemberFavoritePlaceVO(String lineId, String placeId, String placeName, Double rating, String address,
			String photoUrl, String mapUrl, Double latitude, Double longitude, Integer type, Timestamp createDate) {
		super();
		this.lineMemberFavoritePlacePK = new LineMemberFavoritePlacePK(lineId, placeId);
		this.placeName = placeName;
		this.rating = rating;
		this.address = address;
		this.photoUrl = photoUrl;
		this.mapUrl = mapUrl;
		this.latitude = latitude;
		this.longitude = longitude;
		this.type = type;
		this.createDate = createDate;
	}

	public LineMemberFavoritePlaceVO(LineMemberFavoritePlacePK lineMemberFavoritePlacePK) {
		super();
		this.lineMemberFavoritePlacePK = lineMemberFavoritePlacePK;
	}

	public LineMemberFavoritePlacePK getLineMemberFavoritePlacePK() {
		return lineMemberFavoritePlacePK;
	}

	public void setLineMemberFavoritePlacePK(LineMemberFavoritePlacePK lineMemberFavoritePlacePK) {
		this.lineMemberFavoritePlacePK = lineMemberFavoritePlacePK;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getMapUrl() {
		return mapUrl;
	}

	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "LineMemberFavoritePlaceVO [lineMemberFavoritePlacePK=" + lineMemberFavoritePlacePK + ", lineId="
				+ lineMemberFavoritePlacePK.getLineId() + ", placeId=" + lineMemberFavoritePlacePK.getPlaceId() + ", placeName=" + placeName + ", rating=" + rating + ", address="
				+ address + ", photoUrl=" + photoUrl + ", mapUrl=" + mapUrl + ", latitude=" + latitude + ", longitude="
				+ longitude + ", type=" + type + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
   
}
