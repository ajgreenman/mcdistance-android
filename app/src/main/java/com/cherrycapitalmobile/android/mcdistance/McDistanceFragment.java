package com.cherrycapitalmobile.android.mcdistance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class McDistanceFragment extends Fragment {
    TextView mMcDistanceTextView, mMilesTextView;
    Button mViewMcDistanceButton, mCalculateDistanceButton, mGetDirectionsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mcdistance, parent, false);

        mMcDistanceTextView = (TextView) v.findViewById(R.id.mcdistanceTextView);
        mMilesTextView = (TextView) v.findViewById(R.id.milesTextView);

        mViewMcDistanceButton = (Button) v.findViewById(R.id.view_mcdistance_button);
        mCalculateDistanceButton = (Button) v.findViewById(R.id.calculate_distance_button);
        mGetDirectionsButton = (Button) v.findViewById(R.id.directions_button);

        return v;
    }
}
