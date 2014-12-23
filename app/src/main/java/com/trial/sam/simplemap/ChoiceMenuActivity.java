package com.trial.sam.simplemap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class ChoiceMenuActivity extends Activity {

    private Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_menu);

        b1=(Button)findViewById(R.id.computer_lab);
        b1.getBackground().setColorFilter(new LightingColorFilter(0x001E359D, 0x001E359D));

        b2=(Button)findViewById(R.id.print);
        b2.getBackground().setColorFilter(new LightingColorFilter(0x001E359D, 0x001E359D));

        b3=(Button)findViewById(R.id.dining_dollars);
        b3.getBackground().setColorFilter(new LightingColorFilter(0x001E359D, 0x001E359D));
    }


    // Called when the user clicks the Navigate button
    public void findComputerLab(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("pointType",0);
        startActivity(intent);
    }

    // Called when the user clicks the Navigate button
    public void findPrinter(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("pointType",1);
        startActivity(intent);
    }

    // Called when the user clicks the Navigate button
    public void findDiningDollarsLoc(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("pointType",2);
        startActivity(intent);
    }

}
