package com.trial.sam.simplemap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

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

        scrollview = new ScrollView(this);
        LinearLayout linearlayout = new LinearLayout(this);
        linearlayout.setOrientation(LinearLayout.VERTICAL);

        scrollview.addView(linearlayout);
        this.setContentView(scrollview);

        for(int jj = 0; jj<destNames.length-1; jj++)
        {
            LinearLayout linear1 = new LinearLayout(this);
            linear1.setOrientation(LinearLayout.HORIZONTAL);
            linearlayout.addView(linear1);

            //calculate distance

            b = new Button(this);
            b.setText(destNames[jj]);
            b.setId(jj);

            b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            linear1.addView(b);

            final Context ctx = getApplicationContext();
            System.out.println(ctx.toString());

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

}
