package com.trial.sam.simplemap;

import org.simpleframework.xml.Element;
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
public class Detail {

    @ElementList
    private List<String> textList;

    @Element(required=false)
    private Phone phone;

    @Element
    private Hyperlink hyperlink;
    
    public Detail(){
    
    }
    
    public List getTextList(){
        return this.textList;
    }

    public Phone getPhone(){
        return this.phone;
    }

    public Hyperlink getHyperlink(){
        return this.hyperlink;
    }

    public String toString(){
        StringBuilder b = new StringBuilder();
        for(int ii = 0; ii<this.textList.size(); ii++){
            b.append("\t"+this.textList.get(ii)+"\n");
        }
        b.append("\n");

        b.append(this.hyperlink);

        b.append("\n");

        b.append(this.phone);
        
        return b.toString();
    }
}
