package com.retake.retakeapp.schedule;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.retakeapp.R;
import com.retake.retakeapp.base.BaseFragment;
import com.retake.retakeapp.base.BaseModel;
import com.retake.retakeapp.base.ModelFailureResponse;
import com.retake.retakeapp.base.ModelOfflineData;
import com.retake.retakeapp.main.MainActivity;

public class ScheduleFragment extends BaseFragment {
	private ListView lvSchedule;
	private ApiSchedule api = new ApiSchedule();
	private List<ScheduleModel> scheduleList;
	private AdapterSchedule adapter;

	public ScheduleFragment() {
		addApiInterface(api);
	}

	public static ScheduleFragment newInstance() {
		return new ScheduleFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_schedule, container,
				false);

		return rootView;
	}

	// here the shit should be done, dar nu avem conexiune la server
	@Override
	public void onResponse(BaseModel model) {
		if (model instanceof ScheduleListModel) {
			scheduleList = ((ScheduleListModel) model).scheduleList;
			adapter = new AdapterSchedule(
					getActivity().getApplicationContext(), scheduleList);
			lvSchedule.setAdapter(adapter);

		} else if (model instanceof ModelOfflineData
				|| model instanceof ModelFailureResponse) {
			JSONObject mainObject = parseJSONData();
			JSONObject orar;
			try {
				orar = mainObject.getJSONObject("orar");
				JSONArray day1 = orar.getJSONArray("day1");
				JSONArray day2 = orar.getJSONArray("day2");
				JSONArray day3 = orar.getJSONArray("day3");

				scheduleList = new ArrayList<ScheduleModel>();
				scheduleList.add(new ScheduleModel("Friday"));
				for (int i = 0; i < day1.length(); i++) {
					JSONObject obj = day1.getJSONObject(i);
					scheduleList.add(new ScheduleModel("Day 1", obj
							.getString("start"), obj.getString("end"), obj
							.getString("name"), obj.getString("desc")));
					Log.w("NU MERGE IN MM", scheduleList.get(i).toString()
							+ " ");
				}
				scheduleList.add(new ScheduleModel("Saturday"));
				for (int i = 0; i < day2.length(); i++) {
					JSONObject obj = day2.getJSONObject(i);
					scheduleList.add(new ScheduleModel("Day 1", obj
							.getString("start"), obj.getString("end"), obj
							.getString("name"), obj.getString("desc")));
				}
				scheduleList.add(new ScheduleModel("Sunday"));
				for (int i = 0; i < day3.length(); i++) {
					JSONObject obj = day3.getJSONObject(i);
					scheduleList.add(new ScheduleModel("Day 1", obj
							.getString("start"), obj.getString("end"), obj
							.getString("name"), obj.getString("desc")));
				}
				adapter = new AdapterSchedule(getActivity()
						.getApplicationContext(), scheduleList);
				lvSchedule.setAdapter(adapter);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		lvSchedule.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ScheduleModel schedule = (ScheduleModel) parent
						.getItemAtPosition(position);
				Intent intent = new Intent(getActivity(), ScheduleDetails.class);
				intent.putExtra("title", schedule.getName());
				intent.putExtra("start", schedule.getStart());
				intent.putExtra("end", schedule.getEnd());
				intent.putExtra("description", schedule.getDescription());
				startActivity(intent);
			}
		});
	}

	@Override
	public void initUI(View view) {
		lvSchedule = (ListView) getActivity().findViewById(R.id.lvSchedule);

	}

	@Override
	protected void onAfterStart() {
		if (((MainActivity) getActivity()).checkInternet() == true) {
			api.getSchedule();
		} else {
			showConnectionError();
			api.getOfflineSchedule();
		}
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initUI(view);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	/** Reads the JSON from the assets */
	public JSONObject parseJSONData() {
		String jsonString = null;
		JSONObject jsonObject = null;
		try {
			InputStream inputStream = getActivity().getAssets().open(
					"JSONSchedule.txt");
			int sizeOfJSONFile = inputStream.available();
			byte[] bytes = new byte[sizeOfJSONFile];
			inputStream.read(bytes);
			inputStream.close();

			jsonString = new String(bytes, "UTF-8");
			jsonObject = new JSONObject(jsonString);

		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch (JSONException x) {
			x.printStackTrace();
			return null;
		}
		return jsonObject;
	}

}
