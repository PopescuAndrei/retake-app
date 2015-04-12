package com.retake.retakeapp.map;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * ClusterItem represents a marker on the map.
 */

public class CustomPinModel implements ClusterItem {
    private final LatLng mPosition;

    public CustomPinModel(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
    
}