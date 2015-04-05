package com.retake.retakeapp.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retakeapp.R;
import com.retake.retakeapp.base.BaseFragment;
import com.retake.retakeapp.base.BaseModel;

public class ScheduleFragment extends BaseFragment {

	public ScheduleFragment() {
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
		// TODO Auto-generated method stub

	}

	@Override
	public void initUI(View view) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onAfterStart() {
		// TODO Auto-generated method stub

	}

}
