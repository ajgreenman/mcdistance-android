package com.cherrycapitalmobile.android.mcdistance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class McDistanceFragment extends Fragment {
    TextView mMcDistanceTextView, mMilesTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mcdistance, parent, false);

        mMcDistanceTextView = (TextView) v.findViewById(R.id.mcdistanceTextView);
        mMilesTextView = (TextView) v.findViewById(R.id.milesTextView);

        return v;
    }
}
