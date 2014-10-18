package com.cherrycapitalmobile.android.mcdistance;

import android.location.Location;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchPlaces {
    private static final String TAG = "FetchPlaces";
    private static final String NEARBY_SEARCH_URL =
            "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
    private static final String NEARBY_SEARCH_TYPES = "food|restaurant";
    private static final String NEARBY_SEARCH_NAME = "McDonald's";
    private static final String NEARBY_SEARCH_RANKBY = "distance";
    private static final String API_KEY = "AIzaSyBS5rTDOXDQ6sXYBDTyGYUjQpLTe1i90is";

    public Place fetchPlaces(Location location) {
        String json = "";
        String coordinates = location.getLatitude() + "," + location.getLongitude();
        String url = Uri.parse(NEARBY_SEARCH_URL).buildUpon()
                .appendQueryParameter("location", coordinates)
                .appendQueryParameter("types", NEARBY_SEARCH_TYPES)
                .appendQueryParameter("name", NEARBY_SEARCH_NAME)
                .appendQueryParameter("rankby", NEARBY_SEARCH_RANKBY)
                .appendQueryParameter("key", API_KEY).build().toString();
        try {
            json = getUrl(url);
        } catch(IOException ioe) {
            Log.e(TAG, "Failed to fetch items: " + ioe);
        }

        return parseNearestPlace(json);
    }

    private String getUrl(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    private byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];

            while((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }

            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    private Place parseNearestPlace(String json) {
        if(json.equals("")) {
            return null;
        }

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray places = jsonObject.getJSONArray("results");
            JSONObject jsonNearest = places.getJSONObject(0);

            JSONObject jsonLocation =
                    jsonNearest.getJSONObject("geometry").getJSONObject("location");
            Double lat = jsonLocation.getDouble("lat");
            Double lng = jsonLocation.getDouble("lng");

            Location loc = new Location("Google Places API");
            loc.setLatitude(lat);
            loc.setLongitude(lng);

            String address = jsonNearest.getString("vicinity");

            return new Place(loc, address);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON: " + je);
        }

        return null;
    }
}
