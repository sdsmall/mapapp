package com.trial.sam.simplemap;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
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

        getFiles();

        filesToPointSets();

        scrollview = new ScrollView(this);
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
            b.setText(typeNames.get(jj));
            b.setId(jj);

            b.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));

            linear1.addView(b);

            b.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Toast.makeText(getApplicationContext(), "Yipee.."+ v.getId(), Toast.LENGTH_SHORT).show();
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
