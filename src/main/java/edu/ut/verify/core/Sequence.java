package edu.ut.verify.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jerry Wang on 2018/11/5.
 */
public class Sequence {

    private ArrayList<Transition> sequence;

    public int length(){
        return sequence.size();
    }

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

    public void removeLastTransition(){
        sequence.remove(sequence.size()- 1);
    }

    public String toString(){
        return String.join(" -> ", sequence.stream().map(Transition::getEventName).collect(Collectors.toList()));
    }

    public static Sequence clone(Sequence sequence){
        Sequence newSequence = new Sequence();
        for(Transition t : sequence.getSequence())
            newSequence.addTransition(t);

        return newSequence;
    }

    public void printSequence(){
        List<String> names = sequence.stream().map(Transition::getEventName).collect(Collectors.toList());
        System.out.println(String.join(" -> ", names));
    }

    public void printTransitions(){
        for(int i =0;i<this.sequence.size();i++){
            System.out.println(this.sequence.get(i).getEvent().toString());
        }
    }

}
