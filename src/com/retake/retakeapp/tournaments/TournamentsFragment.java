package com.retake.retakeapp.tournaments;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.retakeapp.R;
import com.retake.retakeapp.base.BaseFragment;
import com.retake.retakeapp.base.BaseModel;
import com.retake.retakeapp.utils.JSONParser;

public class TournamentsFragment extends BaseFragment implements
		OnClickListener {
	private LinearLayout linearRound1, linearRound2, linearRound3,
			linearWinner;
	Context mContext;
	private String tournament;
	private String file;
	JSONObject mainJSON;
	private String baseURL = "http://167.114.156.150:8080/full";
	JSONParse asyncTask;

	public TournamentsFragment(String tournament) {

	}

	public void setTournament(String tournament) {
		this.tournament = tournament;

	}

	private void chooseBracket() {
		if (tournament.equals("PGL CS:GO Championship Series Kick-off Season")) {
			file = "CSGOBracket.txt";
			baseURL = baseURL + "CSGOTurnee?seq=0";
		}
		if (checkInternet() == false) {
			mainJSON = parseJSONData(file);
			Log.e("NUUUUUUUUUU neeeeeeeet", "NUUUUUUUUUU neeeeeeeet");
		} else {
			asyncTask = (JSONParse) new JSONParse().execute();
			Log.e("neeeeeeeet", "neeeeeeeet");
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_tournaments,
				container, false);
		mContext = getActivity();
		chooseBracket();
		return rootView;
	}

	@Override
	public void onResponse(BaseModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initUI(View view) {
		linearRound1 = (LinearLayout) view.findViewById(R.id.linearRound1);
		linearRound2 = (LinearLayout) view.findViewById(R.id.linearRound2);
		linearRound3 = (LinearLayout) view.findViewById(R.id.linearRound3);
		linearWinner = (LinearLayout) view.findViewById(R.id.linearWinner);
		try {
			JSONObject bracket = mainJSON.getJSONObject("bracket");
			JSONObject winner = mainJSON.getJSONObject("winner");
			JSONArray round1 = bracket.getJSONArray("round1");
			JSONArray round2 = bracket.getJSONArray("round2");
			JSONArray round3 = bracket.getJSONArray("round3");

			// first round
			for (int i = 0; i < round1.length(); i++) {
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT, 150);

				if (i % 2 == 0) {
					params.bottomMargin = 5;
					params.topMargin = 5;
				} else {
					params.bottomMargin = 100;
					params.topMargin = 5;
				}
				if (i == round1.length() - 1)
					params.bottomMargin = 5;
				params.rightMargin = 20;
				params.leftMargin = 20;

				Button btn = new Button(mContext);
				btn.setTag("buttonR1Team" + i);
				String text = round1.getJSONObject(i).getString("name");
				String status = round1.getJSONObject(i).getString("status");
				btn.setBackgroundResource(R.drawable.round);
				if (status.equals("notstarted")) {
					btn.setText("Not Started");
					btn.setBackgroundColor(mContext.getResources().getColor(
							R.color.notstarted));
					btn.setEnabled(false);
				} else if (status.equals("winner")) {
					btn.setText(text);
					btn.setBackgroundColor(mContext.getResources().getColor(
							R.color.winner));
					btn.setEnabled(true);
				} else {
					btn.setText(text);
					btn.setBackgroundColor(mContext.getResources().getColor(
							R.color.loser));
					btn.setEnabled(true);
				}
				linearRound1.addView(btn, params);
				((Button) view.findViewWithTag("buttonR1Team" + i))
						.setOnClickListener(this);
				if (i % 2 == 0) {
					params = new LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.MATCH_PARENT,
							LinearLayout.LayoutParams.WRAP_CONTENT);
					params.topMargin = 5;
					params.bottomMargin = 5;
					params.gravity = Gravity.CENTER_HORIZONTAL;
					TextView vs = new TextView(mContext);
					vs.setText("vs");
					vs.setGravity(Gravity.CENTER_HORIZONTAL);
					linearRound1.addView(vs, params);

				}
			}

			// second round
			for (int i = 0; i < round2.length(); i++) {
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT, 150);
				if (i % 2 == 0) {
					params.bottomMargin = 130;
					params.topMargin = 110;
				} else {
					params.bottomMargin = 220;
					params.topMargin = 130;
				}
				if (i == round2.length() - 1)
					params.bottomMargin = 5;
				params.rightMargin = 20;
				params.leftMargin = 20;

				Button btn = new Button(mContext);
				btn.setTag("buttonR2Team" + i);
				String text = round2.getJSONObject(i).getString("name");
				String status = round2.getJSONObject(i).getString("status");
				btn.setBackgroundResource(R.drawable.round);
				if (status.equals("notstarted")) {
					btn.setText("Not Started");
					btn.setBackgroundColor(mContext.getResources().getColor(
							R.color.notstarted));
					btn.setEnabled(false);
				} else if (status.equals("winner")) {
					btn.setText(text);
					btn.setBackgroundColor(mContext.getResources().getColor(
							R.color.winner));
					btn.setEnabled(true);
				} else {
					btn.setText(text);
					btn.setBackgroundColor(mContext.getResources().getColor(
							R.color.loser));
					btn.setEnabled(true);
				}
				linearRound2.addView(btn, params);
				((Button) view.findViewWithTag("buttonR2Team" + i))
						.setOnClickListener(this);

				if (i % 2 == 0) {
					params = new LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.MATCH_PARENT,
							LinearLayout.LayoutParams.WRAP_CONTENT);
					params.topMargin = 5;
					params.bottomMargin = 5;
					params.gravity = Gravity.CENTER_HORIZONTAL;
					TextView vs = new TextView(mContext);
					vs.setText("vs");
					vs.setGravity(Gravity.CENTER_HORIZONTAL);
					linearRound2.addView(vs, params);

				}
			}

			// third round
			for (int i = 0; i < round3.length(); i++) {
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.MATCH_PARENT, 150);
				if (i % 2 == 0) {
					params.bottomMargin = 380;
					params.topMargin = 330;
				} else {
					params.bottomMargin = 330;
					params.topMargin = 380;
				}
				if (i == round3.length() - 1)
					params.bottomMargin = 5;
				params.rightMargin = 20;
				params.leftMargin = 20;

				Button btn = new Button(mContext);
				btn.setTag("buttonR3Team" + i);
				String text = round3.getJSONObject(i).getString("name");
				String status = round3.getJSONObject(i).getString("status");
				btn.setBackgroundResource(R.drawable.round);
				if (status.equals("notstarted")) {
					btn.setText("Not Started");
					btn.setBackgroundColor(mContext.getResources().getColor(
							R.color.notstarted));
					btn.setEnabled(false);
				} else if (status.equals("winner")) {
					btn.setText(text);
					btn.setBackgroundColor(mContext.getResources().getColor(
							R.color.winner));
					btn.setEnabled(true);
				} else {
					btn.setText(text);
					btn.setBackgroundColor(mContext.getResources().getColor(
							R.color.loser));
					btn.setEnabled(true);
				}
				linearRound3.addView(btn, params);
				((Button) view.findViewWithTag("buttonR3Team" + i))
						.setOnClickListener(this);
				if (i % 2 == 0) {
					params = new LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.MATCH_PARENT,
							LinearLayout.LayoutParams.WRAP_CONTENT);
					params.topMargin = 5;
					params.bottomMargin = 5;
					params.gravity = Gravity.CENTER_HORIZONTAL;
					TextView vs = new TextView(mContext);
					vs.setText("vs");
					vs.setGravity(Gravity.CENTER_HORIZONTAL);
					linearRound3.addView(vs, params);

				}
			}

			// winner

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT, 150);
			params.topMargin = 820;
			params.rightMargin = 20;
			params.leftMargin = 20;

			Button btn = new Button(mContext);
			btn.setTag("buttonWinner");
			String text = winner.getString("name");
			String status = winner.getString("status");
			btn.setBackgroundResource(R.drawable.round);
			if (status.equals("notstarted")) {
				btn.setText("Not Started");
				btn.setBackgroundColor(mContext.getResources().getColor(
						R.color.notstarted));
				btn.setEnabled(false);
			} else if (status.equals("winner")) {
				btn.setText(text);
				btn.setBackgroundColor(mContext.getResources().getColor(
						R.color.winner));
				btn.setEnabled(true);
			} else {
				btn.setText(text);
				btn.setBackgroundColor(mContext.getResources().getColor(
						R.color.loser));
				btn.setEnabled(true);
			}
			linearWinner.addView(btn, params);
			((Button) view.findViewWithTag("buttonWinner"))
					.setOnClickListener(this);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onAfterStart() {
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		if (checkInternet() == false)
			initUI(view);
	}

	/** Reads the JSON from the assets */
	public JSONObject parseJSONData(String file) {
		String jsonString = null;
		JSONObject jsonObject = null;
		try {
			InputStream inputStream = getActivity().getAssets().open(file);
			int sizeOfJSONFile = inputStream.available();
			byte[] bytes = new byte[sizeOfJSONFile];
			inputStream.read(bytes);
			inputStream.close();

			jsonString = new String(bytes, "UTF-8");
			jsonObject = new JSONObject(jsonString);

		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} catch (JSONException x) {
			x.printStackTrace();
			return null;
		}
		return jsonObject;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(mContext, TeamDetails.class);
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

	private class JSONParse extends AsyncTask<String, String, JSONObject> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected JSONObject doInBackground(String... args) {
			JSONParser jParser = new JSONParser();
			JSONObject json = jParser.getJSONFromUrl(baseURL);
			return json;
		}

		@Override
		protected void onPostExecute(JSONObject json) {

			mainJSON = json;
			initUI(getView());

		}

	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		getActivity().getSupportFragmentManager().beginTransaction()
				.remove(this).commit();
		Log.e("il distruuuuuuge", "mmasiiiiiiiiiii");
	}

	/** check for internet connection */
	public boolean checkInternet() {
		ConnectivityManager cm = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			return false;
		} else
			return true;
	}
}
