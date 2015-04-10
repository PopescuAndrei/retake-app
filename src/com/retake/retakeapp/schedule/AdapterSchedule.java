package com.retake.retakeapp.schedule;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
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

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.schedule_item, null);

			viewHolder.tvFrom = (TextView) convertView
					.findViewById(R.id.tvFrom);
			viewHolder.tvTo = (TextView) convertView.findViewById(R.id.tvTo);
			viewHolder.tvEventName = (TextView) convertView
					.findViewById(R.id.tvEventName);
			viewHolder.tvDay = (TextView) convertView.findViewById(R.id.tvDay);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (Holder) convertView.getTag();
		}
		if (position < scheduleList.size()) {
			viewHolder.tvEventName
					.setText(scheduleList.get(position).getName());
			viewHolder.tvFrom.setText(scheduleList.get(position).getStart());
			viewHolder.tvTo.setText(scheduleList.get(position).getEnd());
			viewHolder.tvDay.setText(scheduleList.get(position).getDay());
		}
		return convertView;
	}

	public static class Holder {
		TextView tvFrom, tvTo, tvEventName, tvDay;
	}

}
