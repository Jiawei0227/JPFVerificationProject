package edu.ut.verify.core;

import edu.ut.verify.core.state.EndState;
import edu.ut.verify.core.state.StartState;
import edu.ut.verify.core.state.State;

import java.util.List;
import java.util.Map;

/**
 * Created by Jerry Wang on 2018/11/5.
 */
public class StateChart {

    private StartState startState;

    private EndState endState;

    private Map<State, List<Transition>> stateTransitionMap;

    public StartState getStartState() {
        return startState;
    }

    public void setStartState(StartState startState) {
        this.startState = startState;
    }

    public EndState getEndState() {
        return endState;
    }

    public void setEndState(EndState endState) {
        this.endState = endState;
    }

    public Map<State, List<Transition>> getStateTransitionMap() {
        return stateTransitionMap;
    }

    public void setStateTransitionMap(Map<State, List<Transition>> stateTransitionMap) {
        this.stateTransitionMap = stateTransitionMap;
    }
}
