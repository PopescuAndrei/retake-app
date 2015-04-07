package com.retake.retakeapp.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retakeapp.R;
import com.retake.retakeapp.base.BaseFragment;
import com.retake.retakeapp.base.BaseModel;
import com.retake.retakeapp.utils.*;

public class TournamentsFragment extends BaseFragment {
	ImageMap mImageMap;
	
	public TournamentsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_tournaments,
				container, false);

		 // find the image map in the view
		mImageMap = (ImageMap) rootView.findViewById(R.id.map);
	   
        
        // add a click handler to react when areas are tapped
        mImageMap.addOnImageMapClickedHandler(new ImageMap.OnImageMapClickedHandler()
        {
			@Override
			public void onImageMapClicked(int id, ImageMap imageMap)
			{
				// when the area is tapped, show the name in a 
				// text bubble
				imageMap.showBubble(id);
			}

			@Override
			public void onBubbleClicked(int id)
			{
				// react to info bubble for area being tapped
			}
		});
        mImageMap.setImageResource(R.drawable.ladder);
        
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
