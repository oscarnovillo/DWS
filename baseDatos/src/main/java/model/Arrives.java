/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.api.client.util.Key;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Arrives {

    @Key
    private ArrayList arrives;

    public Arrives() {
    }

    public ArrayList getArrives() {
        return arrives;
    }

    public void setArrives(ArrayList arrives) {
        this.arrives = arrives;
    }

    @Override
    public String toString() {
        return this.arrives.size() + " "; //To change body of generated methods, choose Tools | Templates.
    }
}
