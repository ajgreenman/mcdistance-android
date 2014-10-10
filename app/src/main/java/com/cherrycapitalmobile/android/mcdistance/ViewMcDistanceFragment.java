package com.cherrycapitalmobile.android.mcdistance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class ViewMcDistanceFragment extends SupportMapFragment {
    private GoogleMap mGoogleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        mGoogleMap = getMap();
        mGoogleMap.setMyLocationEnabled(true);

        return v;
    }
}
