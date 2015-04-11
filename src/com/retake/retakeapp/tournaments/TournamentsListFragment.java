package com.retake.retakeapp.tournaments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.retakeapp.R;
import com.retake.retakeapp.base.BaseFragment;
import com.retake.retakeapp.base.BaseModel;

public class TournamentsListFragment extends BaseFragment {
	private ListView lvTournamentList;
	private TournamentsAdapter adapter;
	private List<TournamentModel> tournamentsList = new ArrayList<TournamentModel>();

	public TournamentsListFragment() {
	}

	public static TournamentsListFragment newInstance() {
		return new TournamentsListFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_tournament_list,
				container, false);

		return rootView;
	}

	// here the shit should be done, dar nu avem conexiune la server
	@Override
	public void onResponse(BaseModel model) {

	}

	@Override
	public void initUI(View view) {
		lvTournamentList = (ListView) getActivity().findViewById(
				R.id.lvTournamentsList);
		if (tournamentsList.isEmpty()) {
			tournamentsList.add(new TournamentModel(
					"PGL CS:GO Championship Series Kick-off Season",
					R.drawable.ic_csgo));
			tournamentsList.add(new TournamentModel(
					"StarLadder StarSeries XII – Dota 2", R.drawable.ic_dota));
			tournamentsList.add(new TournamentModel(
					"PGL Cupa Facultatilor – Hearhtstone",
					R.drawable.ic_hearthstone));
			tournamentsList
					.add(new TournamentModel(
							"PGL Cupa Liceelor – League of Legends",
							R.drawable.ic_lol));
			tournamentsList.add(new TournamentModel(
					"PGL Cupa scolilor – Minecraft", R.drawable.ic_minecraft));
			tournamentsList
					.add(new TournamentModel(
							"PGL Spring Champions of the Storm 2015",
							R.drawable.ic_hos));
			tournamentsList.add(new TournamentModel(
					"PGL Spring Legends of the Rift 2015", R.drawable.ic_lotr));
			tournamentsList.add(new TournamentModel(
					"PGL Spring Tavern Tales 2015", R.drawable.ic_hearthstone));
		}
		adapter = new TournamentsAdapter(getActivity().getApplicationContext(),
				tournamentsList);
		lvTournamentList.setAdapter(adapter);
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