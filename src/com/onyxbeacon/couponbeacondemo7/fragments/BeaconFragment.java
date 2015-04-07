package com.onyxbeacon.couponbeacondemo7.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.retakeapp.R;
import com.onyxbeacon.OnyxBeaconAPIListener;
import com.onyxbeacon.OnyxBeaconManager;
import com.onyxbeacon.couponbeacondemo7.receivers.IBeaconReceiver;
import com.onyxbeaconservice.IBeacon;
import com.retake.retakeapp.base.BaseFragment;
import com.retake.retakeapp.base.BaseModel;

/**
 * Created by Work 2 on 10/15/2014.
 */
public class BeaconFragment extends BaseFragment implements OnyxBeaconAPIListener {
    private OnyxBeaconManager mManager;
    private ListView mListView;
    private Activity mFragmentActivity;
    private BeaconAdapter mBeaconAdapter;
    private IBeaconReceiver iBeaconReceiver;
    private String BEACONS_INTENT_FILTER;
    private boolean receiverRegistered = false;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentActivity = this.getActivity();
        mListView = new ListView(mFragmentActivity);
        mBeaconAdapter = new BeaconAdapter(mFragmentActivity);
        mListView.setAdapter(mBeaconAdapter);
        iBeaconReceiver = new IBeaconReceiver(this);

        // Register receiver
        BEACONS_INTENT_FILTER = getActivity().getPackageName() + ".content.ibeacons";
        getActivity().registerReceiver(iBeaconReceiver, new IntentFilter(BEACONS_INTENT_FILTER));
        receiverRegistered = true;

        return mListView;
    }

    public void onResume() {
        super.onResume();

        // Register receiver
        BEACONS_INTENT_FILTER = getActivity().getPackageName() + ".content.ibeacons";
        getActivity().registerReceiver(iBeaconReceiver, new IntentFilter(BEACONS_INTENT_FILTER));
        receiverRegistered = true;

    }

    public void onPause() {
        super.onPause();
        // Unregister receiver
        if (receiverRegistered) {
            getActivity().unregisterReceiver(iBeaconReceiver);
            receiverRegistered = false;
        }
    }

    @Override
    public void didRangeBeaconsInRegion(final List<IBeacon> beacons) {
        Activity currentActivity = getActivity();
        if (currentActivity != null) {
            currentActivity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    mBeaconAdapter.setBeaconList(beacons);
                }
            });
        }
    }

    @Override
    public void onContentReceived(List<? extends Parcelable> content) {

    }

    @Override
    public void onError(int errorCode, Exception e) {

    }

    class BeaconAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private List<IBeacon> mBeaconList = new ArrayList<IBeacon>();

        public BeaconAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mBeaconList.size();
        }

        @Override
        public Object getItem(int i) {
            return mBeaconList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return mBeaconList.get(i).getMinor();
        }

        public void setBeaconList(List<IBeacon> beacons) {
            if (beacons != null) {
                for (IBeacon beacon : beacons) {
                    if (!mBeaconList.contains(beacon)) {
                        mBeaconList.add(beacon);
                    } else {
                        mBeaconList.set(mBeaconList.indexOf(beacon), beacon);
                    }
                }
            }
            notifyDataSetChanged();
        }

        public View getView(int position,
                            View convertView,
                            ViewGroup parent) {
            View rowView = convertView;
            TextView beaconName;
            if (rowView == null) {
                rowView = mInflater.inflate(R.layout.item_beacon, parent, false);
                beaconName = (TextView) rowView.findViewById(R.id.beacon_name);
                rowView.setTag(beaconName);
            } else {
                beaconName = (TextView) rowView.getTag();
            }
            IBeacon beacon = mBeaconList.get(position);
            beaconName.setText(beacon.getMajor() + "/" + beacon.getMinor() + " RSSI " + beacon.getRssi());
            return rowView;
        }
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
