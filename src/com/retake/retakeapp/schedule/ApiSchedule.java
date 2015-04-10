package com.retake.retakeapp.schedule;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
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

	public void getOfflineSchedule() {
		onGetSchedule(null, "", 0);
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
				if (seq == 0) {
					JSONObject orar = json.getJSONObject("orar");
					JSONArray day1 = orar.getJSONArray("day1");
					JSONArray day2 = orar.getJSONArray("day2");
					JSONArray day3 = orar.getJSONArray("day3");

					List<ScheduleModel> scheduleList = new ArrayList<ScheduleModel>();
					for (int i = 0; i < day1.length(); i++) {
						JSONObject obj = day1.getJSONObject(i);
						scheduleList.add(new ScheduleModel("Day 1", obj
								.getString("start"), obj.getString("end"), obj
								.getString("name"), obj.getString("desc")));
					}
					for (int i = 0; i < day2.length(); i++) {
						JSONObject obj = day2.getJSONObject(i);
						scheduleList.add(new ScheduleModel("Day 2", obj
								.getString("start"), obj.getString("end"), obj
								.getString("name"), obj.getString("desc")));
					}
					for (int i = 0; i < day3.length(); i++) {
						JSONObject obj = day3.getJSONObject(i);
						scheduleList.add(new ScheduleModel("Day 3", obj
								.getString("start"), obj.getString("end"), obj
								.getString("name"), obj.getString("desc")));
					}
					for (ScheduleModel sm : scheduleList) {
						Log.w("sdfasfsafSA", sm.toString());
					}
					onGetSchedule(scheduleList, "", 1);
				} else {
					onGetSchedule(null, "no update", 0);
				}
			} catch (JSONException e) {
				onGetSchedule(null, "no update", 0);
				e.printStackTrace();
			}
		}
	}

	public void onGetSchedule(List<ScheduleModel> list, String error, int seq) {
		if (apiListener != null) {
			if (seq == 1) {
				if (list.size() != 0) {
					ScheduleListModel scheduleListModel = new ScheduleListModel();
					scheduleListModel.scheduleList = list;
					apiListener.onResponse(scheduleListModel);
				} else if (list.size() == 0) {
					ModelOfflineData mod = new ModelOfflineData();
					apiListener.onResponse(mod);
				}
			} else {
				ModelOfflineData mod = new ModelOfflineData();
				apiListener.onResponse(mod);
			}
		} else {
			ModelOfflineData mod = new ModelOfflineData();
			apiListener.onResponse(mod);
			Log.i("api null", "yes");
		}
	}

}