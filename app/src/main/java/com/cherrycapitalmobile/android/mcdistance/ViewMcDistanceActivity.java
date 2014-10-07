package com.cherrycapitalmobile.android.mcdistance;

import android.support.v4.app.Fragment;

/**
 * Created by Anjru on 10/7/2014.
 */
public class ViewMcDistanceActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new ViewMcDistanceFragment();
    }
}
