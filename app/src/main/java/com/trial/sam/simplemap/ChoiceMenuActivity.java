package com.trial.sam.simplemap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import maplistconverter.PointSet;

public class ChoiceMenuActivity extends Activity {
    Button b;
    ScrollView scrollview;
    ArrayList<InputStream> files;
    ArrayList<PointSet> sets;
    ArrayList<String> typeNames;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        getFiles();

        filesToPointSets();

        scrollview = new ScrollView(this);
        scrollview.setBackgroundResource(R.drawable.cathy_blue);
        LinearLayout linearlayout = new LinearLayout(this);
        linearlayout.setOrientation(LinearLayout.VERTICAL);

        scrollview.addView(linearlayout);
        this.setContentView(scrollview);

        for(int jj = 0; jj<typeNames.size(); jj++)
        {
            LinearLayout linear1 = new LinearLayout(this);
            linear1.setOrientation(LinearLayout.HORIZONTAL);
            linearlayout.addView(linear1);
            b = new Button(this);
            Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
            b.setTextSize(15);
            b.setTypeface(tf);
            b.setText(typeNames.get(jj));
            b.getBackground().setAlpha(70);
            b.setTextColor(Color.parseColor("#1E359D"));
            b.setId(jj);
            b.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));

            linear1.addView(b);

            final Context ctx = getApplicationContext();
            System.out.println(ctx.toString());

            final int index = jj;

            b.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    PointSet curr = sets.get(index);
                    String[] destNames = curr.getDestNames();
                    double[] destLat = curr.getLatitudes();
                    double[] destLong = curr.getLongitudes();

                    Intent intent = new Intent(ctx,DistanceActivity.class);
                    intent.putExtra("destNames",destNames);
                    intent.putExtra("latitudes",destLat);
                    intent.putExtra("longitudes",destLong);
                    startActivity(intent);
                }
            });
        }
    }

    public void getFiles(){

        AssetManager assetManager = getResources().getAssets();

        String[] test = new String[0];
        try {
            test = assetManager.list("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> fileNames = new ArrayList<>();

        for(int ii = 0; ii<test.length; ii++){
            if(test[ii].contains(".txt")){
                fileNames.add(test[ii]);
            }
        }

        files = new ArrayList<>();
        for(int jj = 0; jj<fileNames.size(); jj++){
            try {
                files.add(assetManager.open(fileNames.get(jj)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void filesToPointSets(){
        sets = new ArrayList<>();
        typeNames = new ArrayList<>();

        for(int ii = 0; ii<files.size(); ii++){
            try {
                sets.add(new PointSet(files.get(ii)));
                typeNames.add(sets.get(ii).getPointType());
            }
            catch(IOException e){

            }
        }
    }

}
