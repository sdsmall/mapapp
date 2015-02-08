package com.trial.sam.simplemap;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Sam on 2/4/2015.
 */
public class CustomDialogClass extends Dialog implements android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no, pc;
    public TextView text;
    final int index;
    String[] destNames;
    double[] latitudes;
    double[] longitudes;
    String[] details;
    final String message;

    public CustomDialogClass(Activity a, int index, String message, String[] destNames, double[] latitudes, double[] longitudes, String[] details) {
        super(a);

        // TODO Auto-generated constructor stub
        this.c = a;
        this.index = index;
        this.message = message;
        this.destNames = destNames;
        this.latitudes = latitudes;
        this.longitudes = longitudes;
        this.details = details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        pc = (Button) findViewById(R.id.btn_panther_central);
        text = (TextView) findViewById(R.id.txt_dia);
        text.setText(message);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        pc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:

                Intent intent = new Intent(c,MapsActivity.class);
                Bundle b = new Bundle();
                b.putDouble("lat", latitudes[index]);
                b.putDouble("lon", longitudes[index]);
                intent.putExtras(b);
                intent.putExtra("latitudes",latitudes);
                intent.putExtra("longitudes",longitudes);
                intent.putExtra("details",details);
                intent.putExtra("destNames",destNames);

                c.startActivity(intent);

                dismiss();
                break;
            case R.id.btn_no:
                dismiss();
                break;
            case R.id.btn_panther_central:
                //call panther central
                dismiss();
                break;
            default:
                dismiss();
                break;
        }
        dismiss();
    }
}