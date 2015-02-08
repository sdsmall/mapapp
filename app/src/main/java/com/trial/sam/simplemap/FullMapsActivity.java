package com.trial.sam.simplemap;

import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;

import java.util.regex.Pattern;

public class FullMapsActivity extends FragmentActivity implements OnMarkerClickListener{

    LatLng meTemp;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Button b1;

    String[] destNames;
    double[] latitudes;
    double[] longitudes;
    String[] details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //replace with phone gps location
        meTemp = new LatLng(40.439084,-79.954291);

        setContentView(R.layout.activity_full_maps);
        setUpMapIfNeeded();


        //draw navigate button
        b1=(Button)findViewById(R.id.back_button);
        b1.setTextSize(17);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");
        b1.getBackground().setColorFilter(new LightingColorFilter(0x001E359D, 0x001E359D));
        b1.setTypeface(tf);


        Log.e("create end tag","reached end");
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

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

    private void setUpMap() {

        Intent intent = getIntent();
        destNames = intent.getStringArrayExtra("destNames");
        latitudes = intent.getDoubleArrayExtra("latitudes");
        longitudes = intent.getDoubleArrayExtra("longitudes");
        details = intent.getStringArrayExtra("details");

        mMap.setMyLocationEnabled(true);

        mMap.setOnMarkerClickListener(this);
        //Location myLoc = mMap.getMyLocation();
        //LatLng me = new LatLng(myLoc.getLatitude(),myLoc.getLongitude());

        mMap.addMarker(new MarkerOptions().position(meTemp).title("Me"));

        //replace with loop creating markers dynamically
        for(int jj = 0; jj<destNames.length - 1; jj++){

            final LatLng dest = new LatLng(latitudes[jj],longitudes[jj]);

            mMap.addMarker(new MarkerOptions().position(dest).title(String.valueOf(jj)).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_resized)));

        }

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(meTemp, 14, 0, 0)));
    }

    @Override
    public boolean onMarkerClick(Marker arg0) {

        String[] message = details[Integer.valueOf(arg0.getTitle())].split(Pattern.quote("~"));
        StringBuilder builder = new StringBuilder();
        builder.append("Destination:");
        builder.append("\n");
        builder.append(destNames[Integer.valueOf(arg0.getTitle())]);
        builder.append("\n\n");
        for(int ii = 0; ii<message.length; ii++){
            builder.append(message[ii]);
            builder.append("\n");
        }

        System.out.println(builder.toString());

        CustomDialogClass cdd=new CustomDialogClass(FullMapsActivity.this,Integer.valueOf(arg0.getTitle()),builder.toString(),destNames,latitudes,longitudes,details);
        cdd.show();

        return true;
    }

    // Called when the user clicks the Navigate button
    public void backToChoices(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ChoiceMenuActivity.class);
        startActivity(intent);
    }



}
