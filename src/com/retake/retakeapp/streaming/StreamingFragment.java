package com.retake.retakeapp.streaming;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class StreamingFragment extends BaseFragment {
	private ListView lvStreaming;
	private ApiStreaming api;
	private List<StreamingModel> streamingList;
	private AdapterStreaming adapter;
	private List<StreamingModel> temporary;
	private Context context;

	public StreamingFragment() {

	}

	public static StreamingFragment newInstance() {
		return new StreamingFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_streaming,
				container, false);

		return rootView;
	}

	@Override
	public void onResponse(BaseModel model) {
		if (model instanceof StreamingListModel) {
			streamingList = ((StreamingListModel) model).streamingList;
			adapter.setItems(streamingList);
		} else if (model instanceof ModelFailureResponse) {
			Log.i("add schedule failure",
					((ModelFailureResponse) model).getDescription());
		}
	}

	@Override
	protected void onAfterStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initUI(View view) {
		lvStreaming = (ListView) getActivity().findViewById(R.id.lvStreaming);
		temporary = new ArrayList<StreamingModel>();
		StreamingModel model = new StreamingModel("LOL Streaming",
				Uri.parse("http://www.twitch.tv/voyboy/mobile/"));
		temporary.add(model);
		temporary.add(model);
		temporary.add(model);
		temporary.add(model);
		adapter = new AdapterStreaming(getActivity().getApplicationContext(),
				temporary);
		lvStreaming.setAdapter(adapter);
		lvStreaming.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				StreamingModel streaming = (StreamingModel) parent
						.getItemAtPosition(position);
				Intent intent = new Intent(Intent.ACTION_VIEW, streaming
						.getStreamingLink());
				intent.addCategory(Intent.CATEGORY_BROWSABLE);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		initUI(view);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		context = getActivity().getApplicationContext();
	}

}