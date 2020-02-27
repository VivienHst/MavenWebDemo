package dev.vivienhuang.mavenwebdemo.entity.viewmodel.linemessage;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LinePlaceVM {
/*
 * createLinePlaceVO(String lineId, String placeId, String placeName,
			double rating, String address, String photoUrl, String mapUrl, double latitude, double longitude) {*/
	private String placeId;
	
	private String placeName;
	
	private Double rating;
	
	private String address;
	
	private String photoUrl;
	
	private String mapUrl;
	
	private Double latitude;
	
	private Double longitude;
	
	public LinePlaceVM() {
		super();
	}

	public LinePlaceVM(String placeId, String placeName, Double rating, String address, String photoUrl, String mapUrl,
			Double latitude, Double longitude) {
		super();
		this.placeId = placeId;
		this.placeName = placeName;
		this.rating = rating;
		this.address = address;
		this.photoUrl = photoUrl;
		this.mapUrl = mapUrl;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
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
	
	@Override
	public String toString() {
		ObjectMapper Obj = new ObjectMapper(); 
	    String jsonStr = "";
        try { 
            jsonStr = Obj.writeValueAsString(this); 
            // Displaying JSON String 
        } catch (IOException e) { 
            System.out.println(e.getMessage()); 
            e.printStackTrace(); 
        } 
		return jsonStr;
	}

}
