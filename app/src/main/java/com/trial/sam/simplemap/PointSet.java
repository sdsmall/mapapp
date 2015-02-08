package com.trial.sam.simplemap;

/**
 * Created by Sam on 2/2/2015.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class PointSet {
    private String pointType;

    private ArrayList<String> destNames;
    private ArrayList<Double> latitudes;
    private ArrayList<Double> longitudes;
    private ArrayList<String> details;

    public PointSet(InputStream fis) throws IOException{
        destNames = new ArrayList<>();
        latitudes = new ArrayList<>();
        longitudes = new ArrayList<>();
        details = new ArrayList<>();

        BufferedReader br = null;

        try{
            //Construct BufferedReader from InputStreamReader
            br = new BufferedReader(new InputStreamReader(fis));

            String line = null;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if(count == 0){
                    this.pointType = line;
                }
                else{
                    String[] segments = line.split(Pattern.quote("|"));

                    destNames.add(segments[0]);
                    latitudes.add(Double.valueOf(segments[1]));
                    longitudes.add(Double.valueOf(segments[2]));
                    details.add(segments[3]);

                }
                count++;
            }

        } catch(IOException e){

        }
        finally{
            br.close();
            fis.close();
        }
    }

    public String getPointType(){
        return this.pointType;
    }

    public String[] getDestNames(){
        String[] dest = new String[this.destNames.size()];
        for(int ii = 0; ii<this.destNames.size(); ii++){
            dest[ii] = this.destNames.get(ii);
        }
        return dest;
    }

    public double[] getLatitudes(){
        double[] lat = new double[this.latitudes.size()];
        for(int ii = 0; ii<this.latitudes.size(); ii++){
            lat[ii] = this.latitudes.get(ii);
        }
        return lat;
    }

    public double[] getLongitudes(){
        double[] lon = new double[this.longitudes.size()];
        for(int ii = 0; ii<this.longitudes.size(); ii++){
            lon[ii] = this.longitudes.get(ii);
        }
        return lon;
    }

    public String[] getDetails(){
        String[] det = new String[this.details.size()];
        for(int ii = 0; ii<this.details.size(); ii++){
            det[ii] = this.details.get(ii);
        }
        return det;
    }

}

