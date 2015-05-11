package org.utd.bd.recsys.bean;

import java.math.BigDecimal;

public class Artist {
	private String id, mdbId;
	private String name;
	private String location;
	private BigDecimal latitude, longitude;
	private String _track;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMdbId() {
		return mdbId;
	}

	public void setMdbId(String mdbId) {
		this.mdbId = mdbId;
	}

	public String getName() {
		return name; 
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String get_track() {
		return _track;
	}

	public void set_track(String _track) {
		this._track = _track;
	}

}
