/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.presenter.entity;

import java.io.Serializable;


/**
 *
 * @author jiqin
 */
public class Presenter implements Serializable,Cloneable{
   
    private String id;
    private String name;
    
    public Presenter()
    {
        this.id="";
        this.name="";
    }
    
    public Presenter(String id, String name) {
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
