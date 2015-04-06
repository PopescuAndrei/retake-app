package com.retake.retakeapp.map;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.retake.retakeapp.base.BaseFragment;
import com.retake.retakeapp.base.BaseModel;

public class FragmentMap extends BaseFragment implements
		GoogleApiClient.ConnectionCallbacks,
		GoogleApiClient.OnConnectionFailedListener {
	private GoogleMap mMap; // Might be null if Google Play services APK is not
	private Button buttonTakeOnEvent;

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
		// mMap.addTileOverlay(new TileOverlayOptions()
		// .tileProvider(new CustomTileProvider(getResources().getAssets())));

		mMap.setBuildingsEnabled(false);
		mMap.setIndoorEnabled(true);
		LatLng polivalentaEntry = new LatLng(44.405180, 26.110692);
		LatLng polivalentaMid = new LatLng(44.405383, 26.110211);

		GroundOverlayOptions polivalentaMap = new GroundOverlayOptions()
				.image(BitmapDescriptorFactory
						.fromResource(R.drawable.floorplan))
				.position(polivalentaMid, 90f).bearing(304);

		mMap.addGroundOverlay(polivalentaMap);

		CameraUpdate upd = CameraUpdateFactory.newLatLngZoom(polivalentaEntry,
				15);
		mMap.moveCamera(upd);

		mMap.addMarker(new MarkerOptions().position(polivalentaEntry).title(
				"Dreamhack entrance"));
		mMap.addMarker(new MarkerOptions()
				.position(new LatLng(44.405249, 26.109954)).title("Retake")
				.snippet("We are ready to take over the world!"));

		mMap.setMyLocationEnabled(true);

	}

	@Override
	public void onResume() {
		mMap = null;
		super.onResume();
		setUpMapIfNeeded();
	}

	public void zoomOnEvent() {
		LatLng polivalentaEntry = new LatLng(44.405180, 26.110692);

		CameraPosition cameraPosition = new CameraPosition.Builder()
				.target(polivalentaEntry) // Sets the center of the map to
											// Mountain
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
