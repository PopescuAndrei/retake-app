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

}
