package com.trial.sam.simplemap;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sam
 */

@Root
public class PointSet {

    @Attribute(name = "name")
    private String name;

    @ElementList(entry="point", inline=true)
    private List<Point> pointList;
    
    public PointSet(){

    }
    
    public String getName(){
        return this.name;
    }
    
    public List getPointList(){
        return this.pointList;
    }

    public String toString(){
        StringBuilder b = new StringBuilder();
        
        b.append("Pointset name: "+this.name+"\n");
        for(int ii=0; ii<this.pointList.size(); ii++){
            b.append("\n"+this.pointList.get(ii)+"\n");
        }
        
        return b.toString();
    }
}
