package com.retake.retakeapp.notifications;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.retakeapp.R;
import com.retake.retakeapp.base.BaseFragment;
import com.retake.retakeapp.base.BaseModel;
import com.retake.retakeapp.base.ModelFailureResponse;
import com.retake.retakeapp.streaming.ApiStreaming;

public class NotificationFragment extends BaseFragment {
	private ListView lvNotification;
	private ApiStreaming api;
	private List<NotificationModel> notificationsList;
	private AdapterNotifications adapter;
	private List<NotificationModel> temporary;
	private Context context;

	public NotificationFragment() {

	}

	public static NotificationFragment newInstance() {
		return new NotificationFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_notification,
				container, false);

		return rootView;
	}

	@Override
	public void onResponse(BaseModel model) {
		if (model instanceof NotificationListModel) {
			notificationsList = ((NotificationListModel) model).notificationsList;
			adapter.setItems(notificationsList);
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
		lvNotification = (ListView) getActivity().findViewById(
				R.id.lvNotification);
		temporary = new ArrayList<NotificationModel>();
		NotificationModel model = new NotificationModel(
				"Title",
				"Lorem impsum dolores bla bla lorem impsum dolores bla blaLorem impsum dolores bla bla lorem impsum dolores bla bla");
		temporary.add(model);
		temporary.add(model);
		temporary.add(model);
		temporary.add(model);
		adapter = new AdapterNotifications(getActivity()
				.getApplicationContext(), temporary);
		lvNotification.setAdapter(adapter);
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