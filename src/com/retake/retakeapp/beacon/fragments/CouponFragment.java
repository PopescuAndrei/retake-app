package com.retake.retakeapp.beacon.fragments;

import java.util.List;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.retakeapp.R;
import com.onyxbeacon.OnyxBeaconApplication;
import com.onyxbeacon.OnyxBeaconCouponListener;
import com.onyxbeacon.OnyxBeaconManager;
import com.onyxbeacon.model.web.Coupon;
import com.retake.retakeapp.base.BaseFragment;
import com.retake.retakeapp.base.BaseModel;
import com.retake.retakeapp.beacon.listeners.BluetoothListener;
import com.retake.retakeapp.beacon.receivers.BluetoothReceiver;
import com.retake.retakeapp.beacon.receivers.CouponReceiver;

public class CouponFragment extends BaseFragment implements
		OnyxBeaconCouponListener, AdapterView.OnItemClickListener,
		BluetoothListener {

	private OnyxBeaconManager mManager;
	private ListView mListView;
	private CouponAdapter mAdapter;
	private BluetoothReceiver mBluetoothReceiver;
	private CouponReceiver mCouponReceiver;
	private SharedPreferences mSharedPref;
	private static final String COUPONS_NEW_COUNTER = "couponsNewCounter";
	private String COUPONS_INTENT_FILTER;
	private boolean receiverRegistered = false;
	private OnyxBeaconCouponListener mCouponListener;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View couponsView = inflater.inflate(R.layout.fragment_coupons,
				container, false);
		mListView = (ListView) couponsView.findViewById(R.id.couponList);
		mAdapter = new CouponAdapter(getActivity());
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
		mCouponListener = this;
		return couponsView;
	}

	@Override
	public void onViewCreated(View v, Bundle savedInstanceState) {
		super.onViewCreated(v, savedInstanceState);

		mSharedPref = getActivity().getSharedPreferences(
				getActivity().getPackageName(), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = mSharedPref.edit();
		editor.putInt(COUPONS_NEW_COUNTER, 0);
		editor.commit();

		mBluetoothReceiver = new BluetoothReceiver(this);
		getActivity().registerReceiver(mBluetoothReceiver,
				new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));

		mCouponReceiver = new CouponReceiver(this);
		COUPONS_INTENT_FILTER = getActivity().getPackageName() + ".coupons";
		getActivity().registerReceiver(mCouponReceiver,
				new IntentFilter(COUPONS_INTENT_FILTER));
		receiverRegistered = true;
	}

	@Override
	public void onResume() {
		super.onResume();
		mManager = OnyxBeaconApplication.getOnyxBeaconManager(this
				.getActivity());
		getActivity().registerReceiver(mCouponReceiver,
				new IntentFilter(COUPONS_INTENT_FILTER));
		getActivity().registerReceiver(mBluetoothReceiver,
				new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
		receiverRegistered = true;
		mAdapter.refreshList();
	}

	@Override
	public void onPause() {
		super.onPause();
		if (receiverRegistered) {
			getActivity().unregisterReceiver(mBluetoothReceiver);
			getActivity().unregisterReceiver(mCouponReceiver);
			receiverRegistered = false;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
		Coupon coupon = mAdapter.getItem(i);
		OnyxBeaconApplication.startCouponDetailActivity(this.getActivity(),
				coupon);
	}

	@Override
	public void onCouponsReceived(final List<Coupon> couponList) {
		Activity activity = getActivity();
		if (activity != null) {
			getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					System.out.println("No of coupons received first = "
							+ couponList.size());
					mAdapter.setCouponList(couponList);
				}
			});
		} else {
			mAdapter.setCouponList(couponList);
		}
	}

	@Override
	public void onError(int errorCode, Exception e) {
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public void enableBluetooth() {
		mManager = OnyxBeaconApplication.getOnyxBeaconManager(this
				.getActivity().getApplicationContext());
		mManager.enableBluetooth();
	}

	@Override
	public void onStateTurnOn() {
		bluetoothBtnSetVisible(false);
		mManager.setForegroundMode(true);
		getActivity().registerReceiver(mCouponReceiver,
				new IntentFilter(COUPONS_INTENT_FILTER));
	}

	@Override
	public void onStateTurnOff() {
		// bluetoothBtnSetVisible(true);
		// mManager.enableBluetooth();
	}

	private void bluetoothBtnSetVisible(boolean visible) {
		// MainActivity mainActivity = (MainActivity) getActivity();
		// Menu menu = mainActivity.getMenu();
		// MenuItem bluetoothMenuItem = menu.findItem(R.id.action_bluetooth);
		// bluetoothMenuItem.setVisible(visible);
	}

	@Override
	public void onResponse(BaseModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initUI(View view) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onAfterStart() {
		// TODO Auto-generated method stub

	}
}
