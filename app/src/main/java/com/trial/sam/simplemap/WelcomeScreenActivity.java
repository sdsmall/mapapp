package com.trial.sam.simplemap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Button;
import android.graphics.LightingColorFilter;
import android.content.Intent;

@TargetApi(11)
public class WelcomeScreenActivity extends Activity {

    private ImageView iv, iv2;
    private ImageView testImage;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome_screen);

        //draw Pittmaps background
        iv=(ImageView)findViewById(R.id.myimage);
        iv.setBackgroundResource(R.drawable.cathy_blue);

        iv2=(ImageView)findViewById(R.id.testimage);
        iv2.setBackgroundResource(R.drawable.transparent_pittmaps_darker);

        //draw navigate button
        b1=(Button)findViewById(R.id.button2);
        b1.getBackground().setAlpha(70);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        b1.setTypeface(tf);
        b1.setTextSize(15);
        b1.setTextColor(Color.parseColor("#1E359D"));

        //on click switch to choice list class
    }

    // Called when the user clicks the Navigate button
    public void switchToPointTypeList(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ChoiceMenuActivity.class);
        startActivity(intent);
    }
}
