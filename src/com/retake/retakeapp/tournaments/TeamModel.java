package com.retake.retakeapp.tournaments;

public class TeamModel {
	private String teamTitle;
	private String teamMember;
	private String country;
	private String role;
	private String joined;
	private String results;
	public TeamModel(String teamTitle, String teamMember, String country,
			String role, String joined, String results) {
		super();
		this.teamTitle = teamTitle;
		this.teamMember = teamMember;
		this.country = country;
		this.role = role;
		this.joined = joined;
		this.results = results;
	}
	public String getTeamTitle() {
		return teamTitle;
	}
	public void setTeamTitle(String teamTitle) {
		this.teamTitle = teamTitle;
	}
	public String getTeamMember() {
		return teamMember;
	}
	public void setTeamMember(String teamMember) {
		this.teamMember = teamMember;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getJoined() {
		return joined;
	}
	public void setJoined(String joined) {
		this.joined = joined;
	}
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}
	
	
}
