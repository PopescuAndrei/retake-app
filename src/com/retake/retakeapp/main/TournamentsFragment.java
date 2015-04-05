package com.retake.retakeapp.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retakeapp.R;
import com.retake.retakeapp.base.BaseFragment;
import com.retake.retakeapp.base.BaseModel;

public class TournamentsFragment extends BaseFragment {

	public TournamentsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_tournaments,
				container, false);

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
