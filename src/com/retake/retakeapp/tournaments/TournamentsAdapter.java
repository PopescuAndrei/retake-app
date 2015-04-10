package com.retake.retakeapp.tournaments;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.retakeapp.R;
import com.retake.retakeapp.schedule.ScheduleModel;
import com.retake.retakeapp.schedule.AdapterSchedule.Holder;

@SuppressLint("InflateParams")
public class TournamentsAdapter extends BaseAdapter {

	List<TournamentModel> tournamentsList = new ArrayList<TournamentModel>();
	Context context;

	/** constructors */
	public TournamentsAdapter() {
	}

	public TournamentsAdapter(Context context) {
		this.context = context;
	}

	public TournamentsAdapter(Context context, List<TournamentModel> list) {
		this.tournamentsList = list;
		this.context = context;
	}

	/** function to return number of elements in list */
	@Override
	public int getCount() {
		if (tournamentsList != null)
			return tournamentsList.size();
		else
			return 0;
	}

	/** get the element in a specific position */
	@Override
	public Object getItem(int position) {

		if (tournamentsList != null)
			return tournamentsList.get(position);
		else
			return null;
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	/** set the list in the listview and notify the adapter on change */
	public void setItems(List<TournamentModel> items) {
		tournamentsList = items;
		notifyDataSetChanged();
	}

	/** creates the row layout for the links */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Holder viewHolder = new Holder();

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.tournament_item, null);

			viewHolder.tvName = (TextView) convertView
					.findViewById(R.id.tv_tournament_name);
			viewHolder.ivPhoto = (ImageView) convertView
					.findViewById(R.id.iv_tournament_logo);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (Holder) convertView.getTag();
		}
		if (position < tournamentsList.size()) {
			TournamentModel t = tournamentsList.get(position);
			viewHolder.tvName.setText(t.getName());
			viewHolder.ivPhoto.setImageDrawable(context.getResources()
					.getDrawable(t.getPhoto()));
		}
		return convertView;
	}

	public static class Holder {
		TextView tvName;
		ImageView ivPhoto;
	}
}