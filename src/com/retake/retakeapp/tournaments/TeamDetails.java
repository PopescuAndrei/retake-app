package com.retake.retakeapp.tournaments;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.retakeapp.R;

public class TeamDetails extends Activity {
	private ImageView ivTeam;
	private TextView tvTeamTitle, tvTeamMember, tvJoined, tvCountry, tvRole,
			tvResults;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_details);
		ivTeam = (ImageView) findViewById(R.id.ivMember);
		tvTeamTitle = (TextView) findViewById(R.id.tvTeamTitle);
		tvTeamMember = (TextView) findViewById(R.id.tvMember);
		tvResults = (TextView) findViewById(R.id.tvResults);
		tvJoined = (TextView) findViewById(R.id.tvJoined);
		tvCountry = (TextView) findViewById(R.id.tvCountry);
		tvRole = (TextView) findViewById(R.id.tvRole);

		Bundle extras = getIntent().getExtras();
		tvTeamTitle.setText(extras.getString("teamTitle"));
		tvTeamMember.setText(extras.getString("member"));
		tvResults.setText(extras.getString("results"));
		tvJoined.setText(extras.getString("joined"));
		tvCountry.setText(extras.getString("country"));
		tvRole.setText(extras.getString("role"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
}
