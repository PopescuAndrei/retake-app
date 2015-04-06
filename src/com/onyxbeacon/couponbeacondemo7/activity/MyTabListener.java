package com.onyxbeacon.couponbeacondemo7.activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;

import com.onyxbeacon.couponbeacondemo7.R;

/**
 * Created by Work 2 on 12/18/2014.
 */
public class MyTabListener implements ActionBar.TabListener {

    private Fragment fragment;

    public MyTabListener(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        fragmentTransaction.replace(R.id.activity_main, fragment);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        fragmentTransaction.remove(fragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
