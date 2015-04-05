package com.retake.retakeapp.base;

public abstract class BaseApiInterface {

	protected BaseApiListener apiListener;

	/**
	 * sets the api listener for an activity so it could listen to the events in
	 * the controller
	 */
	public void setApiListener(BaseApiListener listener) {
		apiListener = listener;
	}

	/** show an alert dialog for internet connection failure */
	protected void showInternetAlertDialog(BaseFragment xlBaseFragment) {
		xlBaseFragment.showConnectionError();
	}

	/** show an alert dialog for internet connection failure */
	protected void showInternetAlertDialog(BaseActivity xlBaseActivity) {
		xlBaseActivity.showConnectionError();
	}

}
