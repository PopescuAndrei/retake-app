package com.retake.retakeapp.schedule;

import java.util.List;

import android.util.Log;

import com.retake.retakeapp.base.BaseApiInterface;
import com.retake.retakeapp.base.ModelFailureResponse;

public class ApiSchedule extends BaseApiInterface {

	// methods for getting the list and parsing it
	// need to talk with ciprian

	public void getSchedule() {
		// gets list and returns list to a
	}

	// will pass an model in on response method of the fragment
	public void onGetSchedule(List<ScheduleModel> list, String error) {
		if (apiListener != null) {
			if (list.size() != 0) {
				ScheduleListModel scheduleListModel = new ScheduleListModel();
				scheduleListModel.scheduleList = list;
				apiListener.onResponse(scheduleListModel);
			} else if (list.size() == 0) {
				ModelFailureResponse mfr = new ModelFailureResponse();
				mfr.setDescription(error);
				apiListener.onResponse(mfr);
			}
		} else {
			Log.i("api null", "yes");
		}
	}

}