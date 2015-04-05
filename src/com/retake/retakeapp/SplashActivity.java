package com.retake.retakeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.example.retakeapp.R;
import com.retake.retakeapp.base.BaseActivity;
import com.retake.retakeapp.main.MainActivity;

public class SplashActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);
		mainHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(SplashActivity.this,
						MainActivity.class);
				startActivity(intent);
			}
		}, 2000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.activity_splash, menu);
		return true;
	}

	@Override
	public void initUI() {
		// TODO Auto-generated method stub
		
	}
}