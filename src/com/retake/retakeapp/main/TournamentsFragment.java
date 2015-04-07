package com.retake.retakeapp.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.retakeapp.R;
import com.retake.retakeapp.base.BaseFragment;
import com.retake.retakeapp.base.BaseModel;

public class TournamentsFragment extends BaseFragment {
	// ImageMap mImageMap;
	private ScrollView scrollView;
	private Button btnFirstTeam;

	public TournamentsFragment() {   
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_tournaments,
				container, false);

		// // find the image map in the view
		// mImageMap = (ImageMap) rootView.findViewById(R.id.map);
		//
		// // add a click handler to react when areas are tapped
		// mImageMap
		// .addOnImageMapClickedHandler(new ImageMap.OnImageMapClickedHandler()
		// {
		// @Override
		// public void onImageMapClicked(int id, ImageMap imageMap) {
		// // when the area is tapped, show the name in a
		// // text bubble
		// Log.e("apaaaaaasa", " ");
		// imageMap.showBubble(id);
		// }
		//
		// @Override
		// public void onBubbleClicked(int id) {
		// // react to info bubble for area being tapped
		// }
		// });
		// mImageMap.setImageResource(R.drawable.ladder);

		return rootView;
	}

	@Override
	public void onResponse(BaseModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initUI(View view) {
		// scrollView = (ScrollView) view.findViewById(R.id.svTournaments);
		btnFirstTeam = (Button) view.findViewById(R.id.btnFirstTeam);
		btnFirstTeam.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity()
						.getApplicationContext(), TeamDetails.class);
				intent.putExtra("teamTitle", "Team: Fnatic");
				intent.putExtra("member", "Robin Ronnquist");
				intent.putExtra("country", "Country: Sweden");
				intent.putExtra("role", "Role: Rifler");
				intent.putExtra("joined", "Joined: August 2013");
				intent.putExtra(
						"results",
						"1st place - ESL One: Katowice 2015 \n\n1st place - King of Majors I \n\n1st place - IOS Pantamera");
				startActivity(intent);

			}
		});
	}

	@Override
	protected void onAfterStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		initUI(view);
	}

}
