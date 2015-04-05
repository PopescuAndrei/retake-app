package com.retake.retakeapp.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.retakeapp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.retake.retakeapp.base.BaseFragment;
import com.retake.retakeapp.base.BaseModel;

public class FragmentMap extends BaseFragment implements
		GoogleApiClient.ConnectionCallbacks,
		GoogleApiClient.OnConnectionFailedListener {
	private GoogleMap mMap; // Might be null if Google Play services APK is not
	Button buttonTakeOnEvent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_map, container,
				false);

		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		buttonTakeOnEvent = (Button) view.findViewById(R.id.buttonTakeOnEvent);
		buttonTakeOnEvent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				zoomOnEvent();

			}
		});
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((MapFragment) getActivity().getFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				setUpMap();
			}
		}
	}

	private void setUpMap() {
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		mMap.addTileOverlay(new TileOverlayOptions()
				.tileProvider(new CustomTileProvider(getResources().getAssets())));
		LatLng Polivalenta = new LatLng(44.4053045, 26.1100732);
		CameraUpdate upd = CameraUpdateFactory.newLatLngZoom(Polivalenta, 15);
		mMap.moveCamera(upd);

		mMap.addMarker(new MarkerOptions().position(Polivalenta).title(
				"DreamHack Bucharest"));

		mMap.setIndoorEnabled(true);
		mMap.setMyLocationEnabled(true);

	}

	@Override
	public void onResume() {
		mMap = null;
		super.onResume();
		setUpMapIfNeeded();
	}

	public void zoomOnEvent() {
		LatLng Polivalenta = new LatLng(44.4053045, 26.1100732);

		CameraPosition cameraPosition = new CameraPosition.Builder()
				.target(Polivalenta) // Sets the center of the map to Mountain
										// View
				.zoom(22) // Sets the zoom
				.bearing(304) // Sets the orientation of the camera to east
				.tilt(0) // Sets the tilt of the camera to 30 degrees
				.build(); // Creates a CameraPosition from the builder
		mMap.animateCamera(CameraUpdateFactory
				.newCameraPosition(cameraPosition));
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mMap = null;
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
