package com.retake.retakeapp.beacon.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.retakeapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.onyxbeacon.OnyxBeaconApplication;
import com.onyxbeacon.model.web.Coupon;

public class CouponAdapter extends BaseAdapter {

	private Context mContext;
	private List<Coupon> mCouponList;
	private LayoutInflater mInflater;
	private SharedPreferences mSharedPref;
	private static final String COUPONS_LIST_ENTRY = "couponsList";
	private static final String SHARED_PREF_NO_ENTRY = "noEntry";
	private Gson gson = new Gson();

	public CouponAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
		mSharedPref = context.getSharedPreferences(context.getPackageName(),
				Context.MODE_PRIVATE);
		String couponsListAsString = mSharedPref.getString(COUPONS_LIST_ENTRY,
				SHARED_PREF_NO_ENTRY);
		if (!couponsListAsString.equals(SHARED_PREF_NO_ENTRY)) {
			mCouponList = gson.fromJson(couponsListAsString,
					new TypeToken<List<Coupon>>() {
					}.getType());
		} else {
			mCouponList = new ArrayList<Coupon>();
		}
		mContext = context;
	}

	public static class ViewHolder {
		public NetworkImageView imageView;
		public TextView titleTextView;
		public TextView descTextView;
	}

	public void setCouponList(List<Coupon> coupons) {
		persistCouponList(coupons);
		notifyDataSetChanged();
	}

	private void persistCouponList(List<Coupon> coupons) {
		if (coupons != null) {
			for (Coupon coupon : coupons) {
				if (!mCouponList.contains(coupon)) {
					mCouponList.add(coupon);
				}
			}
		}
		SharedPreferences.Editor editor = mSharedPref.edit();
		editor.putString(COUPONS_LIST_ENTRY, gson.toJson(mCouponList));
		editor.commit();
	}

	public void refreshList() {
		notifyDataSetChanged();
	}

	public void setCouponListInBackground(List<Coupon> coupons) {
		persistCouponList(coupons);
	}

	@Override
	public int getCount() {
		return mCouponList.size();
	}

	@Override
	public Coupon getItem(int arg0) {
		return mCouponList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return mCouponList.get(arg0).couponId;
	}

	@Override
	public View getView(final int position, View convertView,
			ViewGroup container) {
		View rowView = convertView;
		ViewHolder holder;
		if (rowView == null) {
			rowView = mInflater.inflate(R.layout.item_coupon, container, false);
			holder = new ViewHolder();
			holder.imageView = (NetworkImageView) rowView
					.findViewById(R.id.coupon_imageview);
			holder.titleTextView = (TextView) rowView
					.findViewById(R.id.coupon_name);
			holder.descTextView = (TextView) rowView
					.findViewById(R.id.coupon_desc);
			rowView.setTag(holder);
		} else {
			holder = (ViewHolder) rowView.getTag();
			OnyxBeaconApplication.loadImageAsync(null, holder.imageView);
		}
		final Coupon coupon = mCouponList.get(position);
		OnyxBeaconApplication.loadImageAsync(coupon.imageThumb,
				holder.imageView);
		holder.titleTextView.setText(coupon.name);
		holder.descTextView.setText(coupon.message);
		Button deleteBtn = (Button) rowView.findViewById(R.id.delete_btn);

		deleteBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (position < mCouponList.size()) {
					OnyxBeaconApplication.getOnyxBeaconManager(mContext)
							.deleteCoupon(coupon.couponId);
					mCouponList.remove(position);
					SharedPreferences.Editor editor = mSharedPref.edit();
					editor.putString(COUPONS_LIST_ENTRY,
							gson.toJson(mCouponList));
					editor.commit();
					notifyDataSetChanged();
				}
			}
		});

		return rowView;
	}
}
