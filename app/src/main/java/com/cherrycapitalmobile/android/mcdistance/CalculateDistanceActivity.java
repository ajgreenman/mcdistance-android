package com.cherrycapitalmobile.android.mcdistance;

import android.support.v4.app.Fragment;

public class CalculateDistanceActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new CalculateDistanceFragment();
    }
}
