package com.cherrycapitalmobile.android.mcdistance;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class McDistanceActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new McDistanceFragment();
    }
}
