package com.cs2340.team6.totallyratticalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * once map is available this writes everything
     *
     * @param googleMap The map to work with
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setOnMarkerClickListener(this);

        ArrayList<RatSighting> markers = CSVReader.getReports();
        int i = 0;
        for (RatSighting r : markers) {
            if((!(r.getLatitude().equals("")) && !(r.getLongitude().equals("")) && i < 1000) && dateFits(r)) {
                try {
                    float lat = Float.valueOf(r.getLatitude());
                    float lat = Float.valueOf(r.getLatitude());
                    String longStr = r.getLongitude();
                    longStr = longStr.substring(2);
                    float longitude = Float.valueOf(longStr);
                    LatLng ll = new LatLng(longitude, lat);
                    Marker m = mMap.addMarker(new MarkerOptions().position(ll).title("rat sighting"));
                    m.setTag(r);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(ll));
                    i++;
                }
                catch(Exception e) {}
            }

        }

    }

    /**
     * Goes to info screen for the selected marker
     * @param marker the clicked marker
     * @return true
     */
    public boolean onMarkerClick(final Marker marker) {

        ScrollingActivity.setSelectedRat((RatSighting) marker.getTag());
        Intent intent = new Intent(this, InformationScreen.class);
        startActivity(intent);
        return true;
    }

    /**
     * checks if date of given rat object fits with given parameters
     *
     * @param r rat sighting
     * @return obvious
     */
    private boolean dateFits(RatSighting r) {
        int[] dates = SetDateActivity.getDates();
        if (dates == null) {return true;}
        String date = r.getDate();
        String[]dateArray = date.split("/");
        if (dateArray.length < 3) return false;
        int month = Integer.valueOf(dateArray[0]);
        int day = Integer.valueOf(dateArray[1]);
        int year = Integer.valueOf(dateArray[2]);
        if (year < dates[2] || year > dates[5]) return false;
        if (year == dates[2]) {
            if (month < dates[1] || (month == dates[1] && day < dates[0])) return false;
            return true;
        }
        if (year == dates[5]) {
            if (month > dates[4] || (month == dates[4] && day > dates[3])) return false;
            return true;
        }
        return true;
    }
}
