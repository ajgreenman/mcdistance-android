package com.cherrycapitalmobile.android.mcdistance;

import android.support.v4.app.Fragment;

public class ViewMcDistanceActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new ViewMcDistanceFragment();
    }
}
