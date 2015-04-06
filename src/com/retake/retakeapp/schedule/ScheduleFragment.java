package com.retake.retakeapp.schedule;

import java.util.ArrayList;
import java.util.List;

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

public class ScheduleFragment extends BaseFragment {
	private ListView lvSchedule;
	private ApiSchedule api;
	private List<ScheduleModel> scheduleList;
	private AdapterSchedule adapter;
	private List<ScheduleModel> temporary;

	public ScheduleFragment() {

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

	@Override
	public void onResponse(BaseModel model) {
		if (model instanceof ScheduleListModel) {
			scheduleList = ((ScheduleListModel) model).scheduleList;
			adapter.setItems(scheduleList);
		} else if (model instanceof ModelFailureResponse) {
			Log.i("add schedule failure",
					((ModelFailureResponse) model).getDescription());
		}
	}

	@Override
	public void initUI(View view) {
		lvSchedule = (ListView) getActivity().findViewById(R.id.lvSchedule);
		Log.e("chioruuuuuuuuuuuule", "ajunge in init");
		temporary = new ArrayList<ScheduleModel>();
		ScheduleModel model = new ScheduleModel("monday", "12:00 ", " 14:00",
				"CS GO Tournament", "Sala B");
		temporary.add(model);
		temporary.add(model);
		temporary.add(model);
		temporary.add(model);
		for (ScheduleModel sm : temporary) {
			Log.e("e aici bouuuuuule", sm.getName() + " ");
		}
		adapter = new AdapterSchedule(getActivity().getApplicationContext(),
				temporary);
		lvSchedule.setAdapter(adapter);
		lvSchedule.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ScheduleModel schedule = (ScheduleModel) parent
						.getItemAtPosition(position);
				Intent intent = new Intent(getActivity(), ScheduleDetails.class);
				intent.putExtra("name", schedule.getName());
				intent.putExtra("location", schedule.getLocation());
				intent.putExtra("start", schedule.getStart());
				intent.putExtra("end", schedule.getEnd());
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onAfterStart() {

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

}
