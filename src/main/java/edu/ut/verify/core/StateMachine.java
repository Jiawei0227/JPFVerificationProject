package edu.ut.verify.core;

/**
 * Created by Jerry Wang on 2018/11/5.
 */
public class StateMachine {

    private StateChart stateChart;

    private Sequence sequence;

    public StateMachine(StateChart stateChart){
        this.stateChart = stateChart;
        this.sequence = new Sequence();

        currentState = stateChart.getStartState();
    }

    private State currentState;

    /**
     * Use Verify jpf to search all the path
     * @param e
     */
    private void goNextState(Event e){
        sequence.addEvent(e);
    }



}
