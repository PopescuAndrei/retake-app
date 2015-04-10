package com.retake.retakeapp.base;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.retakeapp.R;

/**
 * Scheleton for creating other activities
 * 
 * @author badeamihaibogdan
 * 
 */
public abstract class BaseFragmentActivity extends
		android.support.v4.app.FragmentActivity implements BaseApiListener {

	protected Handler mainHandler = new Handler();
	private List<BaseApiInterface> apiInterfaces = new ArrayList<BaseApiInterface>();

	/**
	 * call this in onCreate for adding apiInterfaces
	 * 
	 * @param apiInterface
	 */
	protected void addApiInterface(BaseApiInterface apiInterface) {
		apiInterfaces.add(apiInterface);
	}

	/**
	 * Sets apiListeners for this activity
	 * 
	 * @param listener
	 */
	private void setApiInterfacesListener(BaseApiListener listener) {
		int size = apiInterfaces.size();
		for (int i = 0; i < size; i++) {
			apiInterfaces.get(i).setApiListener(listener);
		}
	}

	/**
	 * used to provide an action for the view based on controller's response
	 * 
	 */
	@Override
	public void onResponse(BaseModel model) {
		// TODO Auto-generated method stub

	}

	/**
	 * Implementation for the application states(life cycles)
	 */
	@Override
	protected void onPause() {
		super.onPause();
		setApiInterfacesListener(null);
	}

	@Override
	protected void onResume() {
		super.onResume();
		setApiInterfacesListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	/**
	 * displays toast message<br/>
	 * <br/>
	 * thread agnostic (can be called from any thread)
	 * 
	 * @param text
	 */
	protected void showToast(final String text) {
		if (!TextUtils.isEmpty(text)) {
			if (Looper.myLooper() == Looper.getMainLooper()) {
				Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
			} else {
				mainHandler.post(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(), text,
								Toast.LENGTH_SHORT).show();
					}
				});
			}
		}
	}

	/** displays a dialog box with a given message */
	protected void showDialogBox(String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(message);
		builder.setCancelable(true);
		builder.setPositiveButton(getString(R.string.ok),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		(builder.create()).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	/** check for internet connection */
	public boolean checkInternet() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			return false;
		} else
			return true;
	}
}
