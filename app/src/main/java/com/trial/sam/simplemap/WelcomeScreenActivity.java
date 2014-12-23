package com.trial.sam.simplemap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.graphics.LightingColorFilter;
import android.content.Intent;

public class WelcomeScreenActivity extends Activity {

    private ImageView iv, iv2;
    private ImageView testImage;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        //draw Pittmaps background
        iv=(ImageView)findViewById(R.id.myimage);
        iv.setBackgroundResource(R.drawable.cathy_cropped);

        iv2=(ImageView)findViewById(R.id.testimage);
        iv2.setBackgroundResource(R.drawable.transparent_pittmaps_darker);

        //draw navigate button
        b1=(Button)findViewById(R.id.button2);
        b1.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0x001E359D));

        //on click switch to choice list class
    }

    // Called when the user clicks the Navigate button
    public void switchToPointTypeList(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ChoiceMenuActivity.class);
        startActivity(intent);
    }
}
