package com.onyxbeacon.couponbeacondemo7.receivers;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.onyxbeacon.couponbeacondemo7.listener.BluetoothListener;

/**
 * Created by Work 2 on 1/16/2015.
 */
public class BluetoothReceiver extends BroadcastReceiver {

    private BluetoothListener mBluetoothListener;

    public BluetoothReceiver(BluetoothListener bluetoothListener) {
        mBluetoothListener = bluetoothListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);

        switch (state) {
            case BluetoothAdapter.STATE_CONNECTED:
                break;
            case BluetoothAdapter.STATE_DISCONNECTED:
                break;
            case BluetoothAdapter.STATE_OFF:
                mBluetoothListener.onStateTurnOff();
                break;
            case BluetoothAdapter.STATE_ON:
                mBluetoothListener.onStateTurnOn();
                break;
        }
    }
}
