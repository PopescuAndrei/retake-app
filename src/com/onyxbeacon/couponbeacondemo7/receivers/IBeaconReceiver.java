
package com.onyxbeacon.couponbeacondemo7.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.onyxbeacon.OnyxBeaconAPIListener;
import com.onyxbeaconservice.IBeacon;
import com.onyxbeacon.OnyxBeaconApplication;
import com.onyxbeacon.OnyxBeaconManager;
import java.util.ArrayList;

/**
 * Sample BroadcastReceiver which gets iBeacons found by the IBeaconService application when the application is in
 * background. This receiver will be
 * triggered even if the application process is destroyed.
 *
 * @author Luci
 */
public class IBeaconReceiver extends BroadcastReceiver {

    private OnyxBeaconAPIListener mOnyxBeaconAPIListener;

    public IBeaconReceiver(OnyxBeaconAPIListener onyxBeaconAPIListener) {
        mOnyxBeaconAPIListener = onyxBeaconAPIListener;
    }

    public IBeaconReceiver() {}

    @Override
    public void onReceive(Context context, Intent intent) {
        ArrayList<IBeacon> beacons = intent.getParcelableArrayListExtra(OnyxBeaconApplication.EXTRA_BEACONS);
        System.out.println("BCN received the following beacons " + beacons.size());
        if (mOnyxBeaconAPIListener != null) {
            // Application in foreground
            mOnyxBeaconAPIListener.didRangeBeaconsInRegion(beacons);
        } else {
            // Application in background, show a notification
        }
    }
}
