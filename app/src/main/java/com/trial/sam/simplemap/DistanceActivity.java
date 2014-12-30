package com.trial.sam.simplemap;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import maplistconverter.PointSet;


public class DistanceActivity extends Activity {
    Button b;
    ScrollView scrollview;

    String[] destNames;
    double[] latitudes;
    double[] longitudes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        destNames = intent.getStringArrayExtra("destNames");
        latitudes = intent.getDoubleArrayExtra("latitudes");
        longitudes = intent.getDoubleArrayExtra("longitudes");

        LinearLayout topLayer = new LinearLayout(this);
        topLayer.setOrientation(LinearLayout.VERTICAL);
        topLayer.setBackgroundColor(Color.parseColor("#C5D6E6"));
        b = new Button(this);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Black.ttf");
        b.setTypeface(tf);
        b.setTextSize(15);
        b.getBackground().setAlpha(100);
        b.setTextColor(Color.parseColor("#1E359D"));
        b.setText("Back to choices");
        topLayer.addView(b);
        final Context ctx = getApplicationContext();
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(ctx,ChoiceMenuActivity.class);
                startActivity(intent);
            }
        });

        scrollview = new ScrollView(this);
        scrollview.setBackgroundResource(R.drawable.cathy_blue);
        LinearLayout linearlayout = new LinearLayout(this);
        linearlayout.setOrientation(LinearLayout.VERTICAL);

        scrollview.addView(linearlayout);
        topLayer.addView(scrollview);
        this.setContentView(topLayer);

        for(int jj = 0; jj<destNames.length-1; jj++)
        {
            LinearLayout linear1 = new LinearLayout(this);
            linear1.setOrientation(LinearLayout.HORIZONTAL);
            linearlayout.addView(linear1);

            //calculate distance

            b = new Button(this);
            b.setTextSize(14);
            Typeface tf2 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
            b.setTypeface(tf2);
            b.setTextSize(15);
            b.getBackground().setAlpha(70);
            b.setTextColor(Color.parseColor("#1E359D"));
            b.setText(destNames[jj]);
            b.setId(jj);

            b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            linear1.addView(b);

            final Context currctx = getApplicationContext();
            System.out.println(currctx.toString());

            final int index = jj;

            b.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    Intent intent = new Intent(ctx,MapsActivity.class);
                    intent.putExtra("destNames",destNames);
                    intent.putExtra("latitudes",latitudes);
                    intent.putExtra("longitudes",longitudes);
                    intent.putExtra("index",index);

                    startActivity(intent);
                }
            });
        }
    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    // convert inputstream to String
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader;
        String result = "";
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null)
                result += line;
        }
        catch(Exception e){
        }
        finally{
            inputStream.close();
        }

        return result;

    }

}
