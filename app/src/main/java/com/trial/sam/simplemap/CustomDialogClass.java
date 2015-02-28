package com.trial.sam.simplemap;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
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
    public Button yes, no, pc, detBut;
    public TextView text;
    final int index;
    PointSetList sets;
    int setIndex;
    final String message;
    Detail detail;
    TextView link;

    public CustomDialogClass(Activity a, int index, String message, PointSetList sets, int setIndex, Detail detail) {
        super(a);

        // TODO Auto-generated constructor stub
        this.c = a;
        this.index = index;
        this.message = message;
        this.setIndex = setIndex;
        this.sets = sets;
        this.detail = detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom);
        //yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        if(detail.getPhone() != null){
            detBut = (Button) findViewById(R.id.btn_from_detail);
            detBut.setText(detail.getPhone().getName());
        }
        pc = (Button) findViewById(R.id.btn_panther_central);
        text = (TextView) findViewById(R.id.txt_dia);
        text.setText(message);
        text.setMovementMethod(LinkMovementMethod.getInstance());
        if(detail.getHyperlink()!= null){
            link = (TextView) findViewById(R.id.site);
            link.setText(Html.fromHtml("<a href=\"" + detail.getHyperlink().getLink() + "\">"+detail.getHyperlink().getTitle()+"</a>"));
            link.setMovementMethod(LinkMovementMethod.getInstance());
        }
        //yes.setOnClickListener(this);
        no.setOnClickListener(this);
        pc.setOnClickListener(this);
        detBut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        PointSet currSet = (PointSet) sets.getSets().get(setIndex);
        Point currPoint = (Point) currSet.getPointList().get(index);
        double lat = currPoint.getLatitude();
        double lon = currPoint.getLongitude();

        switch (v.getId()) {
            /*
            case R.id.btn_yes:

                Intent intent = new Intent(c,MapsActivity.class);
                Bundle b = new Bundle();
                b.putDouble("lat", lat);
                b.putDouble("lon", lon);
                intent.putExtras(b);
                intent.putExtra("index",setIndex);
                de.greenrobot.event.EventBus.getDefault().postSticky(sets);

                c.startActivity(intent);

                dismiss();
                break;
               */
            case R.id.btn_no:
                dismiss();
                break;
            case R.id.btn_panther_central:
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "4126481100"));
                c.startActivity(callIntent);
                dismiss();
                break;
            case R.id.btn_from_detail:
                Intent callDetailIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + detail.getPhone().getNumber()));
                c.startActivity(callDetailIntent);
                dismiss();
                break;
            default:
                dismiss();
                break;
        }
        dismiss();
    }
}