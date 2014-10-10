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
    private static final double MINIMUM_DISTANCE = 50.0;

    private int mMcDistance = 1;
    private double mDistance = 0.0;
    private Location mLocation, mFultonLocation;
    private McDistanceManager mMcDistanceManager;
    private TextView mMcDistanceTextView, mMetersTextView, mLatitudeTextView, mLongitudeTextView;
    private Button mViewMcDistanceButton, mCalculateDistanceButton, mGetDirectionsButton,
            mStartButton, mStopButton;

    private BroadcastReceiver mLocationReceiver = new LocationReceiver() {

        @Override
        protected void onLocationReceived(Context context, Location loc) {
            mLocation = loc;
            mDistance = mLocation.distanceTo(mFultonLocation);

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

        mFultonLocation = new Location("Pro J");
        mFultonLocation.setLatitude(42.96361);
        mFultonLocation.setLongitude(-85.6974971);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mcdistance, parent, false);

        mMcDistanceTextView = (TextView) v.findViewById(R.id.mcdistanceTextView);
        mMetersTextView = (TextView) v.findViewById(R.id.metersTextView);
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

            if(mDistance <= MINIMUM_DISTANCE) {
                mMcDistance = 0;
            } else {
                mMcDistance = 1;
            }

            mMcDistanceTextView.setText("McDistance: " + mMcDistance);
            mMetersTextView.setText("Meters: " + mDistance);
        }

        mStartButton.setEnabled(!started);
        mStopButton.setEnabled(started);
    }
}
