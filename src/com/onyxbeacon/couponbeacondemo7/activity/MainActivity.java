package com.onyxbeacon.couponbeacondemo7.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.onyxbeacon.OnyxBeaconApplication;
import com.onyxbeacon.OnyxBeaconManager;
import com.onyxbeacon.couponbeacondemo7.R;
import com.onyxbeacon.couponbeacondemo7.fragments.BeaconFragment;
import com.onyxbeacon.couponbeacondemo7.fragments.CouponFragment;
import com.onyxbeacon.couponbeacondemo7.fragments.SettingsFragment;
import com.onyxbeacon.couponbeacondemo7.logging.LogDialogFragment;
import com.onyxbeacon.couponbeacondemo7.logging.MyLogger;

import java.io.IOException;

/**
 * Created by Work 2 on 9/23/2014.
 */
public class MainActivity extends Activity implements
        LogDialogFragment.LogDialogListener {

    public static final String LOGGER_KEY = "LoggerKey";
    private ActionBar actionBar;
    private MyLogger mLogger;
    private ActionBar.Tab couponsTab, settingsTab, beaconTab;
    public static final String EXTRA_COUPONS = "coupons";
    private CouponFragment couponFragment;
    private SettingsFragment settingsFragment;
    private BeaconFragment beaconFragment;
    private Menu mMenu;
    private OnyxBeaconManager mManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // Initialize the logger
        try {
            mLogger = new MyLogger(this);
        } catch (IOException exception) {
            mLogger = null;
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable(LOGGER_KEY, mLogger);

        couponFragment = new CouponFragment();
        settingsFragment = new SettingsFragment();
        settingsFragment.setArguments(bundle);
        beaconFragment = new BeaconFragment();
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        couponsTab = actionBar.newTab().setText("Coupons");
        settingsTab = actionBar.newTab().setText("Settings");
        beaconTab = actionBar.newTab().setText("Beacons");

        couponsTab.setTabListener(new MyTabListener(couponFragment));
        settingsTab.setTabListener(new MyTabListener(settingsFragment));
        beaconTab.setTabListener(new MyTabListener(beaconFragment));

        actionBar.addTab(couponsTab);
        actionBar.addTab(settingsTab);
        actionBar.addTab(beaconTab);

        // Initialize OnyxBeaconManager
        mManager = OnyxBeaconApplication.getOnyxBeaconManager(this);
        // Enable beacons and coupons retrieval
        mManager.setCouponEnabled(true);
        mManager.setAPIContentEnabled(true);
        mManager.setLogger(mLogger);

    }

    public void onResume() {
        super.onResume();
        if (mManager.isBluetoothAvailable()) {
            // Enable scanner in foreground mode and register receiver
            mManager.setForegroundMode(true);
        } else {
            Toast.makeText(this, "Please turn on bluetooth", Toast.LENGTH_LONG).show();
        }
    }

    public void onPause() {
        super.onPause();
        // Set scanner in background mode
        mManager.setForegroundMode(false);
    }

    public MyLogger getLogger() {
        return mLogger;
    }

    /* Confirm send logs */
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        EditText logName = (EditText) dialog.getDialog().findViewById(R.id.logName);
        settingsFragment.sendLogs(logName.getText().toString());
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.getDialog().cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.custom_menu, menu);
        OnyxBeaconManager onyxManager = OnyxBeaconApplication.getOnyxBeaconManager(this.getApplicationContext());
        if (onyxManager.isBluetoothAvailable()) {
            MenuItem bluetoothMenuItem = menu.findItem(R.id.action_bluetooth);
            bluetoothMenuItem.setVisible(false);
        }
        return true;
    }

    /* Enable bluetooth button */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_bluetooth:
                couponFragment.enableBluetooth();
                break;
            default:
                break;
        }
        return true;
    }

    public Menu getMenu() {
        return mMenu;
    }
}
