package com.retake.retakeapp.streaming;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.retakeapp.R;

public class AdapterStreaming extends BaseAdapter {

	List<StreamingModel> streamingList = new ArrayList<StreamingModel>();
	Context context;

	/** constructors */
	public AdapterStreaming() {
	}

	public AdapterStreaming(Context context) {
		this.context = context;
	}

	public AdapterStreaming(Context context, List<StreamingModel> list) {
		this.streamingList = list;
		this.context = context;
	}

	/** function to return number of elements in list */
	@Override
	public int getCount() {
		if (streamingList != null)
			return streamingList.size();
		else
			return 0;
	}

	/** get the element in a specific position */
	@Override
	public Object getItem(int position) {

		if (streamingList != null)
			return streamingList.get(position);
		else
			return null;
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	/** set the list in the listview and notify the adapter on change */
	public void setItems(List<StreamingModel> items) {
		streamingList = items;
		notifyDataSetChanged();
	}

	/** creates the row layout for the links */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Holder viewHolder = new Holder();

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.streaming_item, null);

			viewHolder.tvStreamingLink = (TextView) convertView
					.findViewById(R.id.tvStreamingLink);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (Holder) convertView.getTag();
		}
		if (position < streamingList.size()) {
			viewHolder.tvStreamingLink.setText(streamingList.get(position)
					.getStreamName());
		}
		return convertView;
	}

	public static class Holder {
		TextView tvStreamingLink;
	}
}
