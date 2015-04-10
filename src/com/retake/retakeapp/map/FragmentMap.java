package com.retake.retakeapp.map;

import android.os.Bundle;
import android.view.InflateException;
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
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
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
	Marker BigMarker;
	MarkerOptions BigMarkerOptions;
	GroundOverlay GO;
	static View v;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		if (v != null) {
			ViewGroup parent = (ViewGroup) v.getParent();
			if (parent != null) {
				parent.removeView(v);

			}

		}
		try {
			v = inflater.inflate(R.layout.fragment_map, container, false);
		} catch (InflateException e) {
		}

		return v;
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

	private void setUpMarkers(CameraPosition position) {
		LatLng polivalentaMid = new LatLng(44.405383, 26.110211);
		LatLng polivalentaEntry = new LatLng(44.405180, 26.110692);

		MarkerOptions EntryMarker = new MarkerOptions()
				.position(polivalentaEntry).title("Dreamhack entrance")
				.visible(true);
		MarkerOptions RetakeMarker = new MarkerOptions()
				.position(new LatLng(44.405249, 26.109954)).title("Retake")
				.snippet("We are ready to take over the world!").visible(true)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.pins));
		// BigMarkerOptions = new MarkerOptions()
		// .position(polivalentaEntry)
		// .title("Retake")
		// .snippet("We are ready to take over the world!")
		// .icon(BitmapDescriptorFactory
		// .fromResource(R.drawable.dreamhackopen));

		mMap.addMarker(EntryMarker);
		mMap.addMarker(RetakeMarker);
		// if (position.zoom <= 17f && BigMarker == null) {
		// BigMarker = mMap.addMarker(BigMarkerOptions);
		// } else {
		// if (BigMarker != null)
		// BigMarker.remove();
		// }
	}

	private void setUpMap() {

		mMap.setOnCameraChangeListener(new OnCameraChangeListener() {

			@Override
			public void onCameraChange(CameraPosition position) {
				setUpMarkers(position);
			}

		});

		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		mMap.getUiSettings().setMapToolbarEnabled(false);
		mMap.setBuildingsEnabled(false);
		mMap.setIndoorEnabled(true);
		LatLng polivalentaEntry = new LatLng(44.405180, 26.110692);
		LatLng polivalentaMid = new LatLng(44.405383, 26.110211);

		GroundOverlayOptions polivalentaMap = new GroundOverlayOptions()
				.image(BitmapDescriptorFactory.fromResource(R.drawable.floor3))
				.position(polivalentaMid, 113f).bearing(-56);

		// GO.remove();
		GO = mMap.addGroundOverlay(polivalentaMap);

		CameraUpdate upd = CameraUpdateFactory.newLatLngZoom(polivalentaEntry,
				15);
		mMap.moveCamera(upd);
		// setUpMarkers();
		mMap.setMyLocationEnabled(true);
		mMap.setPadding(0, 0, 0, 10);
	}

	@Override
	public void onResume() {
		// mMap = null;
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
