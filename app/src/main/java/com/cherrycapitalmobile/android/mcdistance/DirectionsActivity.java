package com.cherrycapitalmobile.android.mcdistance;

import android.support.v4.app.Fragment;

public class DirectionsActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new DirectionsFragment();
    }
}
