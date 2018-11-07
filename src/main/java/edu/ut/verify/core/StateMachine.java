package edu.ut.verify.core;

import edu.ut.verify.core.state.State;

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
    private void goNextState(Transition e){
        sequence.addSequence(e);
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public StateChart getStateChart() {
        return stateChart;
    }

    public void setStateChart(StateChart stateChart) {
        this.stateChart = stateChart;
    }


}
