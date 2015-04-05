package com.retake.retakeapp.schedule;

import java.util.ArrayList;
import java.util.List;

import com.retake.retakeapp.base.BaseModel;

public class ScheduleListModel extends BaseModel {

	List<ScheduleModel> scheduleList;

	public ScheduleListModel() {
		this.scheduleList = new ArrayList<ScheduleModel>();
	}

	public List<ScheduleModel> getScheduleList() {
		return scheduleList;
	}

	public void setList(List<ScheduleModel> scheduleList) {
		this.scheduleList = scheduleList;
	}

}
