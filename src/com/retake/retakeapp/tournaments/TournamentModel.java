package com.retake.retakeapp.tournaments;

public class TournamentModel {
	private String name;
	private int photo;

	public TournamentModel(String name, int photo) {
		super();
		this.name = name;
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhoto() {
		return photo;
	}

	public void setPhoto(int photo) {
		this.photo = photo;
	}

}
