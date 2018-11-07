package edu.ut.verify.core;

import java.util.ArrayList;

/**
 * Created by Jerry Wang on 2018/11/5.
 */
public class Sequence {

    ArrayList<Transition> sequence;


    public Sequence(){
        sequence = new ArrayList<Transition>();
    }

    public ArrayList<Transition> getSequence() {
        return sequence;
    }

    public void setSequence(ArrayList<Transition> sequence) {
        this.sequence = sequence;
    }

    public void addSequence(Transition transition){
        sequence.add(transition);
    }

    public Sequence clone(Sequence sequence){
        Sequence newSequence = new Sequence();
        for(Transition t : sequence.getSequence())
            newSequence.addSequence(t);

        return newSequence;
    }
}
