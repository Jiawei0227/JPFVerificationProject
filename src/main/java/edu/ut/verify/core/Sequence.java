package edu.ut.verify.core;

import java.util.ArrayList;

/**
 * Created by Jerry Wang on 2018/11/5.
 */
public class Sequence {

    ArrayList<Event> sequence;

    public Sequence(){
        sequence = new ArrayList<Event>();
    }

    public ArrayList<Event> getSequence() {
        return sequence;
    }

    public void setSequence(ArrayList<Event> sequence) {
        this.sequence = sequence;
    }

    public void addEvent(Event event){
        sequence.add(event);
    }
}
