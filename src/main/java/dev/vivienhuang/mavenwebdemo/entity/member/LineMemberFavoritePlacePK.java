package dev.vivienhuang.mavenwebdemo.entity.member;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class LineMemberFavoritePlacePK implements Serializable{
	
	private String lineId;
	private String placeId;
	
	public LineMemberFavoritePlacePK() {
		super();
	}
	
	public LineMemberFavoritePlacePK(String lineId, String placeId) {
		super();
		this.lineId = lineId;
		this.placeId = placeId;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lineId == null) ? 0 : lineId.hashCode());
		result = prime * result + ((placeId == null) ? 0 : placeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineMemberFavoritePlacePK other = (LineMemberFavoritePlacePK) obj;
		if (lineId == null) {
			if (other.lineId != null)
				return false;
		} else if (!lineId.equals(other.lineId))
			return false;
		if (placeId == null) {
			if (other.placeId != null)
				return false;
		} else if (!placeId.equals(other.placeId))
			return false;
		return true;
	}
}
