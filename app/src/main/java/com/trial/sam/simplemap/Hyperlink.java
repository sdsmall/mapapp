package com.trial.sam.simplemap;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Sam on 2/23/2015.
 */
@Root
public class Hyperlink {

    @Element
    private String link;

    @Element
    private String title;

    public Hyperlink(){

    }

    public String getLink(){
        return this.link;
    }

    public void setLink(String link){
        this.link = link;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String toString(){
        StringBuilder b = new StringBuilder();

        b.append("Title: "+this.title+"\n");
        b.append("\tLink: "+this.link);

        return b.toString();
    }

}
