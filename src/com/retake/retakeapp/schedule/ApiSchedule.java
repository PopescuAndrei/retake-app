package com.retake.retakeapp.schedule;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.retake.retakeapp.base.BaseApiInterface;
import com.retake.retakeapp.base.ModelFailureResponse;
import com.retake.retakeapp.base.ModelOfflineData;
import com.retake.retakeapp.utils.JSONParser;

public class ApiSchedule extends BaseApiInterface {
	private String baseURL = "http://167.114.156.150:8080/fullOrare?seq=0";
	JSONParse asyncTask;

	public void getSchedule() {
		asyncTask = (JSONParse) new JSONParse().execute();
	}

	public void onGetSchedule(List<ScheduleModel> list, String error, int seq) {
		if (apiListener != null) {
			if (seq == 1) {
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
				ModelOfflineData mod = new ModelOfflineData();
				apiListener.onResponse(mod);
			}
		} else {
			Log.i("api null", "yes");
		}
	}

	private class JSONParse extends AsyncTask<String, String, JSONObject> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected JSONObject doInBackground(String... args) {
			JSONParser jParser = new JSONParser();
			JSONObject json = jParser.getJSONFromUrl(baseURL);
			return json;
		}

		@Override
		protected void onPostExecute(JSONObject json) {
			try {
				int seq = Integer.parseInt(json.getString("seq"));
				if (seq == 1) {
					JSONObject orar = json.getJSONObject("orar");
					JSONArray day1 = orar.getJSONArray("day1");
					JSONArray day2 = orar.getJSONArray("day2");
					JSONArray day3 = orar.getJSONArray("day3");
					List<ScheduleModel> listModel = new ArrayList<ScheduleModel>();
					for (int i = 0; i < day1.length(); i++) {
						JSONObject obj = day1.getJSONObject(i);
						ScheduleModel model = new ScheduleModel();
						model.setDay("Day 1");
						model.setDescription(obj.getString("desc"));
						model.setStart(obj.getString("start"));
						model.setEnd(obj.getString("end"));
						System.out.println(model.toString());
						listModel.add(model);
					}
					for (int i = 0; i < day2.length(); i++) {
						JSONObject obj = day2.getJSONObject(i);
						ScheduleModel model = new ScheduleModel();
						model.setDay("Day 1");
						model.setDescription(obj.getString("desc"));
						model.setStart(obj.getString("start"));
						model.setEnd(obj.getString("end"));
						listModel.add(model);
					}
					for (int i = 0; i < day3.length(); i++) {
						JSONObject obj = day3.getJSONObject(i);
						ScheduleModel model = new ScheduleModel();
						model.setDay("Day 1");
						model.setDescription(obj.getString("desc"));
						model.setStart(obj.getString("start"));
						model.setEnd(obj.getString("end"));
						listModel.add(model);
					}
					onGetSchedule(listModel, "", 1);
				} else {
					onGetSchedule(null, "no update", 0);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}