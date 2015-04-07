package com.retake.retakeapp.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.retakeapp.R;

public class ScheduleDetails extends Activity {
	private TextView tvTime, tvTitle, tvDescription, tvRoom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule_details);
		tvTime = (TextView) findViewById(R.id.tvEventTimeDetails);
		tvTitle = (TextView) findViewById(R.id.tvEventNameDetails);
		tvDescription = (TextView) findViewById(R.id.tvDescriptionDetails);
		tvRoom = (TextView) findViewById(R.id.tvRoomDetails);

		Bundle extras = getIntent().getExtras();
		tvTitle.setText(extras.getString("title"));
		tvDescription.setText(extras.getString("description"));
		tvRoom.setText(extras.getString("room"));
		tvTime.setText(extras.getString("start") + " - "
				+ extras.getString("end"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.schedule_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
