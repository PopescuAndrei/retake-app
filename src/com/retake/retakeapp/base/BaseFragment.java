package com.retake.retakeapp.base;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;

import com.example.retakeapp.R;

public abstract class BaseFragment extends Fragment implements BaseApiListener {

	protected String SCREEN_TITLE = "base";
	private Context context;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.context = getActivity();
	}

	private List<BaseApiInterface> apiInterfaces = new ArrayList<BaseApiInterface>();

	/**
	 * call this in oncreate for adding apiinterfaces<br/>
	 * 
	 * @author Andrei
	 * @param apiInterface
	 */
	protected void addApiInterface(BaseApiInterface apiInterface) {
		apiInterfaces.add(apiInterface);
	}

	/**
	 * sets apilisteners for this activity
	 * 
	 * @author Andrei
	 * @param listener
	 */
	private void setApiInterfacesListener(BaseApiListener listener) {
		int size = apiInterfaces.size();
		for (int i = 0; i < size; i++) {
			apiInterfaces.get(i).setApiListener(listener);
		}
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		setApiInterfacesListener(this);
		onAfterStart();
	}

	@Override
	public void onPause() {
		super.onPause();
		setApiInterfacesListener(null);
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	/**
	 * displays toast message<br/>
	 * <br/>
	 * thread agnostic (can be called from any thread)
	 * 
	 * @param text
	 */
	public void showDialogBox(String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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

	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return false;
	}

	/** show an alert dialog for Internet connection failure */
	protected void showConnectionError() {
		showDialogBox(getString(R.string.api_get_content_fail_no_internet));
	}

	public abstract void initUI(View view);

	protected abstract void onAfterStart();

}
