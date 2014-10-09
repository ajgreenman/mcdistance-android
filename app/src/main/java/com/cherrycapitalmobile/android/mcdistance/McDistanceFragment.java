package com.cherrycapitalmobile.android.mcdistance;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class McDistanceFragment extends Fragment {
    private Location mLocation;
    private McDistanceManager mMcDistanceManager;
    private TextView mMcDistanceTextView, mMilesTextView, mLatitudeTextView, mLongitudeTextView;
    private Button mViewMcDistanceButton, mCalculateDistanceButton, mGetDirectionsButton,
            mStartButton, mStopButton;

    private BroadcastReceiver mLocationReceiver = new LocationReceiver() {

        @Override
        protected void onLocationReceived(Context context, Location loc) {
            mLocation = loc;

            if(isVisible()) {
                updateUI();
            }
        }
    };

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
        mLatitudeTextView = (TextView) v.findViewById(R.id.latitudeTextView);
        mLongitudeTextView = (TextView) v.findViewById(R.id.longitudeTextView);

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

    @Override
    public void onStart() {
        super.onStart();
        getActivity().registerReceiver(mLocationReceiver,
                new IntentFilter(McDistanceManager.ACTION_LOCATION));
    }

    @Override
    public void onStop() {
        getActivity().unregisterReceiver(mLocationReceiver);
        super.onStop();
    }

    private void updateUI() {
        boolean started = mMcDistanceManager.isTrackingLocation();

        if(mLocation != null) {
            mLatitudeTextView.setText(Double.toString(mLocation.getLatitude()));
            mLongitudeTextView.setText(Double.toString(mLocation.getLongitude()));
        }

        mStartButton.setEnabled(!started);
        mStopButton.setEnabled(started);
    }
}
