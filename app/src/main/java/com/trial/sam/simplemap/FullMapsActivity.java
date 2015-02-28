package com.trial.sam.simplemap;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;

import java.util.ArrayList;
import java.util.regex.Pattern;

import de.greenrobot.event.EventBus;

public class FullMapsActivity extends FragmentActivity implements OnMarkerClickListener, LocationListener{

    LatLng meTemp;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Button b1;

    PointSetList sets;
    int index;
    PointSet curr;

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
        //b1.getBackground().setColorFilter(new LightingColorFilter(0x000489B1, 0x000489B1));
        b1.setTypeface(tf);
        b1.setTextColor(0xFFFFFFFF);
        //b1.getBackground().setAlpha(110);


        Log.e("create end tag", "reached end");
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

    @Override
    public void onLocationChanged(Location location) {

        // Getting latitude of the current location
        double latitude = location.getLatitude();

        // Getting longitude of the current location
        double longitude = location.getLongitude();

        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

        // Showing the current location in Google Map
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom in the Google Map
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    private void setUpMap() {

        Intent intent = getIntent();
        index = intent.getIntExtra("index",0);
        sets = (PointSetList) EventBus.getDefault().removeStickyEvent(PointSetList.class);
        curr = (PointSet) sets.getSets().get(index);

        mMap.setOnMarkerClickListener(this);
        //Location myLoc = mMap.getMyLocation();
        //LatLng me = new LatLng(location.getLatitude(),location.getLongitude());

        mMap.setMyLocationEnabled(true);
        mMap.getMyLocation();

        //mMap.addMarker(new MarkerOptions().position(meTemp).title("Me"));

        for(int jj = 0; jj<curr.getPointList().size(); jj++){
            Point currPoint = (Point) curr.getPointList().get(jj);

            final LatLng dest = new LatLng(currPoint.getLatitude(),currPoint.getLongitude());
            if(jj == 0){
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(dest, 14, 0, 0)));
            }

            mMap.addMarker(new MarkerOptions().position(dest).title(String.valueOf(jj)).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_resized)));

        }
    }

    @Override
    public boolean onMarkerClick(Marker arg0) {
        Point currPoint = (Point)curr.getPointList().get(Integer.valueOf(arg0.getTitle()));

        StringBuilder builder = new StringBuilder();
        builder.append("Destination:");
        builder.append("\n");
        builder.append(currPoint.getName());
        builder.append("\n\n");

        Detail detail = currPoint.getDetail();
        for(int ii = 0; ii<detail.getTextList().size(); ii++){
            builder.append(detail.getTextList().get(ii));
            builder.append("\n");
        }

        System.out.println(builder.toString());

        CustomDialogClass cdd=new CustomDialogClass(FullMapsActivity.this,Integer.valueOf(arg0.getTitle()),builder.toString(),sets,index,detail);
        cdd.show();

        return true;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return false;
    }

    // Called when the user clicks the back button
    public void backToChoices(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ChoiceMenuActivity.class);
        startActivity(intent);
    }

}
