package com.retake.retakeapp.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageButton;

import com.example.retakeapp.R;
import com.retake.retakeapp.base.BaseFragment;
import com.retake.retakeapp.base.BaseModel;

public class HomeFragment extends BaseFragment {
	private ImageButton btnMap, btnAchievements, btnTournaments,
			btnNotifications, btnStreaming, btnSchedule;

	public HomeFragment() {
	}

	public static HomeFragment newInstance() {
		return new HomeFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);

		return rootView;
	}

	@Override
	public void onResponse(BaseModel model) {
	}

	@Override
	public void initUI(View view) {
		btnMap = (ImageButton) view.findViewById(R.id.btnMapHome);
		btnAchievements = (ImageButton) view
				.findViewById(R.id.btnAchievementsHome);
		btnNotifications = (ImageButton) view
				.findViewById(R.id.btnNotificationsHome);
		btnSchedule = (ImageButton) view.findViewById(R.id.btnScheduleHome);
		btnStreaming = (ImageButton) view.findViewById(R.id.btnStreamingHome);
		btnTournaments = (ImageButton) view
				.findViewById(R.id.btnTournamentsHome);
		btnMap.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).launchMap(1);
			}
		});
		btnAchievements.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).launchAchievements(2);

			}
		});
		btnNotifications.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).launchNotification(3);

			}
		});
		btnSchedule.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).launchNotification(4);

			}
		});
		btnTournaments.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).launchTournaments(5);

			}
		});
		btnStreaming.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).launchStreaming(6);

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
