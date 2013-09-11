/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.producer.entity;

import java.io.Serializable;

/**
 *
 * @author jiqin
 */
public class Producer implements Serializable,Cloneable{
    private String id;
    private String name;

    public Producer(){
        this.id = "";
        this.name = "";
    }
    
    public Producer(String name){
        this.id="";
        this.name = name;
    }
    
    public Producer(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
}
