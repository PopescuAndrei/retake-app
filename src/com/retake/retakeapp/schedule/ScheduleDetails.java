package com.retake.retakeapp.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.retakeapp.R;

public class ScheduleDetails extends Activity {
	private TextView tvTime, tvTitle, tvDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule_details);
		tvTime = (TextView) findViewById(R.id.tvEventTimeDetails);
		tvTitle = (TextView) findViewById(R.id.tvEventNameDetails);
		tvDescription = (TextView) findViewById(R.id.tvDescriptionDetails);

		Bundle extras = getIntent().getExtras();
		tvTitle.setText(extras.getString("title"));
		tvDescription.setText(extras.getString("description"));
		tvTime.setText(extras.getString("start") + " - "
				+ extras.getString("end"));
	}

}
