package com.trial.sam.simplemap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author Sam
 */

@Root
public class Point {

    @Attribute
    private String name;

    @Element
    private double latitude;

    @Element
    private double longitude;

    @Element
    private Detail detail;
    
    public Point(){
    
    }
    
    public String getName(){
        return this.name;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return this.longitude;
    }

    public Detail getDetail(){
        return this.detail;
    }
    
    public String toString(){
        StringBuilder b = new StringBuilder();
    
        b.append("Point: "+this.name+"\n");
        b.append("("+this.latitude+", "+this.longitude+")\n");
        b.append("Details:\n");
        b.append(this.detail+"\n");
        
        return b.toString();
    }
}
