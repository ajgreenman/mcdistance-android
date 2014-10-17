package com.cherrycapitalmobile.android.mcdistance;

import android.location.Location;

public class Place {
    private Location mLocation;
    private String mAddress;

    public Place(Location location, String address) {
        mLocation = location;
        mAddress = address;
    }

    public Location getLocation() {

        return mLocation;
    }

    public void setLocation(Location mLocation) {
        this.mLocation = mLocation;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }
}
