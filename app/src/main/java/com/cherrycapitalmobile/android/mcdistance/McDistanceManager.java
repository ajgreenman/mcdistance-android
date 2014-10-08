package com.cherrycapitalmobile.android.mcdistance;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

public class McDistanceManager {
    private static final String TAG = "LocationManager";

    public static final String ACTION_LOCATION =
            "com.cherrycapitalmobile.android.mcdistance.ACTION_LOCATION";

    private static McDistanceManager sMcDistanceManager;
    private Context mAppContext;
    private LocationManager mLocationManager;

    private McDistanceManager(Context appContext) {
        mAppContext = appContext;
        mLocationManager = (LocationManager) mAppContext.getSystemService(Context.LOCATION_SERVICE);
    }

    public static McDistanceManager get(Context c) {
        if(sMcDistanceManager == null) {
            sMcDistanceManager = new McDistanceManager(c.getApplicationContext());
        }

        return sMcDistanceManager;
    }

    private PendingIntent getLocationPendingIntent(boolean shouldCreate) {
        Intent broadcast = new Intent(ACTION_LOCATION);
        int flags = shouldCreate ? 0 : PendingIntent.FLAG_NO_CREATE;

        return PendingIntent.getBroadcast(mAppContext, 0, broadcast, flags);
    }

    public void startLocationUpdates() {
        String provider = LocationManager.GPS_PROVIDER;

        PendingIntent pi = getLocationPendingIntent(true);
        mLocationManager.requestLocationUpdates(provider, 5000, 5, pi);
    }

    public void stopLocationUpdates() {
        PendingIntent pi = getLocationPendingIntent(false);

        if(pi != null) {
            mLocationManager.removeUpdates(pi);
            pi.cancel();
        }
    }

    public boolean isTrackingLocation() {
        return getLocationPendingIntent(false) != null;
    }
}