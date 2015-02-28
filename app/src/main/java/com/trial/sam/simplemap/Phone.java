package com.trial.sam.simplemap;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author Sam
 */

@Root
public class Phone {

    @Element(required=false)
    private String number;

    @Element(required=false)
    private String name;
    
    public Phone(){
    
    }
    
    public Phone(String number, String name){
        this.number = number;
        this.name = name;
    }
    
    public String getNumber(){
        return this.number;
    }

    public void setNumber(String number){
        this.number = number;
    }
    
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String toString(){
        StringBuilder b = new StringBuilder();
        
        b.append("Name: "+this.name+"\n");
        b.append("\tNumber: "+this.number);
        
        return b.toString();
    }
}
