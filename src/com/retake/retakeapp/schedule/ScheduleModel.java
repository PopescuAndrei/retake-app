package com.retake.retakeapp.schedule;

public class ScheduleModel {
	private String day;
	private String start;
	private String end;
	private String name;
	private String location;

	public ScheduleModel(String day, String start, String end, String name,String location) {
		super();
		this.day = day;
		this.start = start;
		this.end = end;
		this.name = name;
		this.location = location;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
}
