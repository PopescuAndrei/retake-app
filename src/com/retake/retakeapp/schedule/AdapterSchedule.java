package com.retake.retakeapp.schedule;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.retakeapp.R;

public class AdapterSchedule extends BaseAdapter {

	List<ScheduleModel> scheduleList = new ArrayList<ScheduleModel>();
	Context context;

	/** constructors */
	public AdapterSchedule() {
	}

	public AdapterSchedule(Context context) {
		this.context = context;
	}

	public AdapterSchedule(Context context, List<ScheduleModel> list) {
		this.scheduleList = list;
		this.context = context;
	}

	/** function to return number of elements in list */
	@Override
	public int getCount() {
		if (scheduleList != null)
			return scheduleList.size();
		else
			return 0;
	}

	/** get the element in a specific position */
	@Override
	public Object getItem(int position) {

		if (scheduleList != null)
			return scheduleList.get(position);
		else
			return null;
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	/** set the list in the listview and notify the adapter on change */
	public void setItems(List<ScheduleModel> items) {
		scheduleList = items;
		notifyDataSetChanged();
	}

	/** creates the row layout for the links */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Holder viewHolder = new Holder();

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.schedule_item, null);
		viewHolder.main = (LinearLayout) convertView
				.findViewById(R.id.mainLayoutSchedule);
		viewHolder.tvFrom = (TextView) convertView.findViewById(R.id.tvFrom);
		viewHolder.tvTo = (TextView) convertView.findViewById(R.id.tvTo);
		viewHolder.tvEventName = (TextView) convertView
				.findViewById(R.id.tvEventName);
		viewHolder.tvDash = (TextView) convertView.findViewById(R.id.tvDash);
		viewHolder.tvDay = (TextView) convertView.findViewById(R.id.tvDay);
		convertView.setTag(viewHolder);
		if (position < scheduleList.size()) {
			ScheduleModel sm = scheduleList.get(position);
			viewHolder.tvEventName.setText(sm.getName());
			if (sm.getName().equals("Friday")
					|| sm.getName().equals("Saturday")
					|| sm.getName().equals("Sunday")) {
				viewHolder.tvFrom.setText("");
				viewHolder.tvTo.setText("");
				viewHolder.tvDash.setText("");
				viewHolder.main.setBackgroundResource(R.color.retakeCyan);
				viewHolder.tvEventName.setTextColor(context.getResources()
						.getColor(R.color.white));
				viewHolder.tvEventName.setGravity(Gravity.CENTER);
				viewHolder.tvDay.setText("");
			} else {
				viewHolder.tvFrom.setText(sm.getStart());
				viewHolder.tvTo.setText(sm.getEnd());
				viewHolder.tvDay.setText(sm.getDay());
			}
		}
		return convertView;
	}

	public static class Holder {
		TextView tvFrom, tvTo, tvEventName, tvDay, tvDash;
		LinearLayout main;
	}

}
