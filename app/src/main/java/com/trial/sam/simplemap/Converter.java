package com.trial.sam.simplemap;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.InputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sam
 */
public class Converter {
    
    public PointSet convert(InputStream input){
        
        PointSet test = null;

        try {
            Serializer serializer = new Persister();
            test = serializer.read(PointSet.class, input);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }
}
