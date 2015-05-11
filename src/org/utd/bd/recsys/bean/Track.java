package org.utd.bd.recsys.bean;

public class Track {
	// trackId: line.trackID, songId: line.songID, artistName: line.artistName,
	// title:line.songName
	private String trackId, songId, artistName, title;

	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String id) {
		this.trackId = id;
	}

	public String getSongId() {
		return songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
