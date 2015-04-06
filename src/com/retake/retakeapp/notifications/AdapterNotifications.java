package com.retake.retakeapp.notifications;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.retakeapp.R;

public class AdapterNotifications extends BaseAdapter {

	List<NotificationModel> notificationsList = new ArrayList<NotificationModel>();
	Context context;

	/** constructors */
	public AdapterNotifications() {
	}

	public AdapterNotifications(Context context) {
		this.context = context;
	}

	public AdapterNotifications(Context context, List<NotificationModel> list) {
		this.notificationsList = list;
		this.context = context;
	}

	/** function to return number of elements in list */
	@Override
	public int getCount() {
		if (notificationsList != null)
			return notificationsList.size();
		else
			return 0;
	}

	/** get the element in a specific position */
	@Override
	public Object getItem(int position) {

		if (notificationsList != null)
			return notificationsList.get(position);
		else
			return null;
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	/** set the list in the listview and notify the adapter on change */
	public void setItems(List<NotificationModel> items) {
		notificationsList = items;
		notifyDataSetChanged();
	}

	/** creates the row layout for the links */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Holder viewHolder = new Holder();

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.notification_item, null);

			viewHolder.tvNotificationTitle = (TextView) convertView
					.findViewById(R.id.tvNotificationTitle);
			viewHolder.tvNotificationMessage = (TextView) convertView
					.findViewById(R.id.tvNotificationMessage);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (Holder) convertView.getTag();
		}
		if (position < notificationsList.size()) {
			viewHolder.tvNotificationTitle.setText(notificationsList.get(
					position).getNotificationTitle());
			viewHolder.tvNotificationMessage.setText(notificationsList.get(
					position).getNotificationMessage());
		}
		return convertView;
	}

	public static class Holder {
		TextView tvNotificationTitle;
		TextView tvNotificationMessage;
	}
}
