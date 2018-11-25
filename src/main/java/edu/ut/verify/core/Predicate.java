package edu.ut.verify.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jerry Wang on 2018/11/5.
 */
public class Predicate {

    private String variable;  //variable in this event
    private int[] boundary; // the boundary of the variable
    /**
     * in an event 0 < N <= 10
     * variable = N;
     * boundary = [1,10];
     */

    public Predicate(String var) {
        this.variable = var;
        this.boundary = new int[2];
        this.boundary[0] = -10000;
        //some variable don't have an explicate boundary, set default
        this.boundary[1] = -10000;
    }

    public void setHigh(int high){
        this.boundary[1] = high;
    }

    public void setLow(int low){
        this.boundary[0] = low;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public int[] getBoundary() {
        return boundary;
    }

    public void setBoundary(int[] boundary) {
        this.boundary = boundary;
    }

    public String toString(){
        return ""+boundary[0]+" < "+variable+" < "+boundary[1];
    }
}

