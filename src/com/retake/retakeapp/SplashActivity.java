package com.retake.retakeapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

import com.example.retakeapp.R;
import com.retake.retakeapp.main.MainActivity;

public class SplashActivity extends Activity {
	SharedPreferences settings;
	protected Handler mainHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		settings = getSharedPreferences("prefs", 0);
		boolean firstRun = settings.getBoolean("firstRun", true);
		if (firstRun == true) {
			mainHandler.postDelayed(new Runnable() {
				@Override
				public void run() {

					Intent intent = new Intent(SplashActivity.this,
							MainActivity.class);
					SharedPreferences.Editor editor = settings.edit();
					editor.putBoolean("firstRun", false);
					editor.commit();
					startActivity(intent);
				}
			}, 2000);
		} else {
			finish();
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.activity_splash, menu);
		return true;
	}

}