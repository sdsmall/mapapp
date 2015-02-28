package com.trial.sam.simplemap;

import java.util.ArrayList;

/**
 * Created by Sam on 2/21/2015.
 */
public class PointSetList {

    private ArrayList<PointSet> sets;

    public PointSetList(ArrayList<PointSet> sets){
        this.sets = sets;
    }

    public ArrayList<PointSet> getSets(){
        return this.sets;
    }

}
