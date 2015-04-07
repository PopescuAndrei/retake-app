package com.onyxbeacon.couponbeacondemo7.receivers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.example.retakeapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.onyxbeacon.OnyxBeaconApplication;
import com.onyxbeacon.OnyxBeaconCouponListener;
import com.onyxbeacon.model.web.Coupon;
import com.retake.retakeapp.main.MainActivity;

/**
 * Created by Luci on 5/22/2014.
 */
public class CouponReceiver extends BroadcastReceiver {

    private static String COUPON_KEY = "coupons_key";
    private static String COUPONS_TAG = "coupons_tag";
    private SharedPreferences mSharedPref;
    private Gson gson = new Gson();
    private static final String COUPONS_LIST_ENTRY = "couponsList";
    private static final String COUPONS_NEW_COUNTER = "couponsNewCounter";
    private static final String SHARED_PREF_NO_ENTRY = "noEntry";
    private OnyxBeaconCouponListener mCouponListener;
    private int mCouponCounter = 0;

    public CouponReceiver() {}

    public CouponReceiver(OnyxBeaconCouponListener couponListener) {
        mCouponListener = couponListener;
    }

    @Override public void onReceive(Context context, Intent intent) {

        mSharedPref = context.getSharedPreferences(context.getPackageName(),
                Context.MODE_PRIVATE);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        ArrayList<Coupon> coupons = intent.getParcelableArrayListExtra(OnyxBeaconApplication.EXTRA_COUPONS);


        if (coupons == null || coupons.size() == 0) {
        } else {
            String couponsListAsString = mSharedPref.getString(COUPONS_LIST_ENTRY, SHARED_PREF_NO_ENTRY);
            ArrayList<Coupon> couponsFromStorage = new ArrayList<Coupon>();
            ArrayList<Coupon> newCoupons = new ArrayList<Coupon>();
            if (!couponsListAsString.equals(SHARED_PREF_NO_ENTRY)) {
                couponsFromStorage = (ArrayList<Coupon>)gson.fromJson(couponsListAsString, new TypeToken<List<Coupon>>() {
                }.getType());
                for (Coupon couponReceived : coupons) {
                    boolean couponFound = false;
                    for (Coupon couponFromStorage : couponsFromStorage) {
                        if (couponReceived.couponId
                                == couponFromStorage.couponId) {
                            couponFound = true;
                        }
                    }
                    if (!couponFound) {
                        newCoupons.add(couponReceived);
                    }
                }
            }

            couponsFromStorage.addAll(newCoupons);

            SharedPreferences.Editor editor = mSharedPref.edit();
            editor.putString(COUPONS_LIST_ENTRY, gson.toJson(couponsFromStorage));
            editor.commit();

            mCouponCounter = mSharedPref.getInt(COUPONS_NEW_COUNTER, 0);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            Intent i = new Intent(context, MainActivity.class);
            i.putParcelableArrayListExtra(MainActivity.EXTRA_COUPONS, coupons);
            stackBuilder.addNextIntent(i);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            long[] vibratePattern = {500, 500, 500, 500};
            for (Iterator<Coupon> ci = coupons.iterator(); ci.hasNext();) {
                ++mCouponCounter;
                Coupon c = ci.next();
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(context)
                                .setContentTitle(c.name)
                                .setContentText(c.message)
                                .setContentInfo(mCouponCounter > 1 ? "+ " + (mCouponCounter - 1) + " new more" : "")
                                .setSmallIcon(R.drawable.ic_launcher)
                                .setAutoCancel(true)
                                .setGroup(COUPON_KEY)
                                .setGroupSummary(true)
                                .setVibrate(vibratePattern)
                                .setLights(Color.BLACK, 500, 500)
                                .setSound(notificationSound);

                builder.setContentIntent(resultPendingIntent);
                notificationManager.notify(COUPONS_TAG, 1, builder.build());

                editor.putInt(COUPONS_NEW_COUNTER, mCouponCounter);
                editor.commit();
            }

            if (mCouponListener != null) {
                mCouponListener.onCouponsReceived(coupons);
            }

        }
    }
}
