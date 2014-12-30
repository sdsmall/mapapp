package com.trial.sam.simplemap;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Typeface;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    LatLng meTemp;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Button b1;

    String[] destNames;
    double[] latitudes;
    double[] longitudes;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        meTemp = new LatLng(40.439084,-79.954291);

        Intent intent = getIntent();
        destNames = intent.getStringArrayExtra("destNames");
        latitudes = intent.getDoubleArrayExtra("latitudes");
        longitudes = intent.getDoubleArrayExtra("longitudes");
        index = intent.getIntExtra("index",0);


        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        //draw navigate button

        b1=(Button)findViewById(R.id.back_button);
        b1.setTextSize(15);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Black.ttf");
        b1.getBackground().setColorFilter(new LightingColorFilter(0x001E359D, 0x001E359D));
        b1.setTextColor(Color.WHITE);
        b1.setTypeface(tf);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.setMyLocationEnabled(true);
        //Location myLoc = mMap.getMyLocation();
        //LatLng me = new LatLng(myLoc.getLatitude(),myLoc.getLongitude());

        mMap.addMarker(new MarkerOptions().position(meTemp).title("Me"));

        LatLng curr = new LatLng(latitudes[index], longitudes[index]);

        mMap.addMarker(new MarkerOptions().position(curr).title("Destination"));

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(curr,15,0,0)));

        System.out.println(getUrl(meTemp,curr));
    }

    public String getUrl(LatLng src, LatLng dest){

        StringBuilder urlString = new StringBuilder();

        urlString.append("http://maps.google.com/maps?f=d&hl=en");
        urlString.append("&saddr=");
        urlString.append(Double.toString(src.latitude));
        urlString.append(",");
        urlString.append(Double.toString(src.longitude));
        urlString.append("&daddr=");// to
        urlString.append(Double.toString(dest.latitude));
        urlString.append(",");
        urlString.append(Double.toString(dest.longitude));
        urlString.append("&ie=UTF8&0&om=0&output=kml");

        return urlString.toString();
    }

    // Called when the user clicks the Navigate button
    public void backToChoices(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DistanceActivity.class);
        intent.putExtra("destNames",destNames);
        intent.putExtra("latitudes",latitudes);
        intent.putExtra("longitudes",longitudes);
        startActivity(intent);
    }

}
