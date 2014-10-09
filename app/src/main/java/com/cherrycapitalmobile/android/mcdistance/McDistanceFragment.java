package com.cherrycapitalmobile.android.mcdistance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class McDistanceFragment extends Fragment {
    private McDistanceManager mMcDistanceManager;
    private TextView mMcDistanceTextView, mMilesTextView;
    private Button mViewMcDistanceButton, mCalculateDistanceButton, mGetDirectionsButton,
            mStartButton, mStopButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mMcDistanceManager = McDistanceManager.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mcdistance, parent, false);

        mMcDistanceTextView = (TextView) v.findViewById(R.id.mcdistanceTextView);
        mMilesTextView = (TextView) v.findViewById(R.id.milesTextView);

        mViewMcDistanceButton = (Button) v.findViewById(R.id.view_mcdistance_button);
        mViewMcDistanceButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ViewMcDistanceActivity.class);
                startActivity(i);
            }
        });

        mCalculateDistanceButton = (Button) v.findViewById(R.id.calculate_distance_button);
        mCalculateDistanceButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CalculateDistanceActivity.class);
                startActivity(i);
            }
        });

        mGetDirectionsButton = (Button) v.findViewById(R.id.directions_button);
        mGetDirectionsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), DirectionsActivity.class);
                startActivity(i);
            }
        });

        mStartButton = (Button) v.findViewById(R.id.startButton);
        mStartButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mMcDistanceManager.startLocationUpdates();
                updateUI();
            }
        });

        mStopButton = (Button) v.findViewById(R.id.stopButton);
        mStopButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mMcDistanceManager.stopLocationUpdates();
                updateUI();
            }
        });

        updateUI();

        return v;
    }

    private void updateUI() {
        boolean started = mMcDistanceManager.isTrackingLocation();

        mStartButton.setEnabled(!started);
        mStopButton.setEnabled(started);
    }
}
