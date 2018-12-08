package edu.ut.verify.core;

import edu.ut.verify.core.exception.NoInitialStateException;
import edu.ut.verify.core.state.State;
import edu.ut.verify.core.statechart.StateChart;
import edu.ut.verify.core.statechart.Transition;

import java.util.*;

/**
 * Created by Jerry Wang on 2018/11/5.
 */
public class StateMachine {

    private StateChart stateChart;
    private Set<String> variableSet;
    private List<Sequence> sequences;

    public Set<String> getVariableSet() {
        return variableSet;
    }

    public void setVariableSet(Set<String> variableSet) {
        this.variableSet = variableSet;
    }

    /**
     * contains
     */
    private static final int circleCount = 1;

    public StateMachine(StateChart stateChart){
        this.stateChart = stateChart;

        this.sequences = new ArrayList<>();

        this.variableSet = new HashSet<>();

        currentState = stateChart.getStartState();
    }

    private State currentState;


    public StateChart getStateChart() {
        return stateChart;
    }

    /**
     * start process of the state chart sequence
     */
    public void process() throws NoInitialStateException{

        Sequence sequence = new Sequence();
        
        if(currentState == null)
            throw new NoInitialStateException("No Start State Found!");
        if(stateChart.getEndState() == null)
            throw new NoInitialStateException("No End State Found!");

        System.out.println("---------------------------------------Path Print:--------------------------------------------");

        HashMap<Transition, Integer> countMap = new HashMap<>();
        dfs(sequence, countMap);
    }

    /**
     * dfs + backtrack
     * @param sequence
     * @param countMap
     */
    private void dfs(Sequence sequence, HashMap<Transition, Integer> countMap){
        if(currentState.equals(stateChart.getEndState())){
            sequences.add(Sequence.clone(sequence));
            return;
        }

        List<Transition> transitionList = stateChart.getStateTransitionMap().get(currentState);

        for( Transition transition : transitionList ){

            if(transition.getEvent().getPredicate().getVariable() != null)
                variableSet.add(transition.getEvent().getPredicate().getVariable());

            //System.out.println(currentState.getName() + " -> " + transition.getEvent().getName());
            sequence.addTransition(transition);
            State nextState = transition.getToState();

            if(countMap.getOrDefault(transition,0) == circleCount) {
                sequence.removeLastTransition();
                continue;
            }


            countMap.put(transition, countMap.getOrDefault(transition, 0 ) + 1);
            //dfs
            this.currentState = nextState;
            dfs(sequence, countMap);

            countMap.put(transition, countMap.get(transition) - 1);
           // System.out.println(countMap.get(nextState));

            this.currentState = transition.getFromState();
            sequence.removeLastTransition();
        }
    }

    public void printSequence(){
        sequences.sort((o1,o2) -> (o1.length() - o2.length()));
        for(int i = 0 ; i < sequences.size() ; i ++){
            System.out.print(String.format("Path %2d : ",i+1));
            sequences.get(i).printSequence();
        }
    }


    public List<Sequence> getSequences() {
        sequences.sort((o1,o2) -> (o1.length() - o2.length()));
        return sequences;
    }

    public void setSequences(List<Sequence> sequences) {
        this.sequences = sequences;
    }

    public static int getCircleCount() {
        return circleCount;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

}
