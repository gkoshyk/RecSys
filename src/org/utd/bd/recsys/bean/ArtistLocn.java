package org.utd.bd.recsys.bean;

public class ArtistLocn {
	private String name;
	private int count;

	public int getCount() {
		return count;
	}

	public ArtistLocn(String name, int count) {
		super();
		this.name = name;
		this.count = count;
	}
	public ArtistLocn() {}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
