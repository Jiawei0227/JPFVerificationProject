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

    public void addTransition(Transition transition){
        sequence.add(transition);
    }

    public void removeTransition(Transition transition) {
        sequence.remove(transition);
    }

    public static Sequence clone(Sequence sequence){
        Sequence newSequence = new Sequence();
        for(Transition t : sequence.getSequence())
            newSequence.addTransition(t);

        return newSequence;
    }
}
