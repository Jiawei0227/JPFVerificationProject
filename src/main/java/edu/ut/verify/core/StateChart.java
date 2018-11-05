package edu.ut.verify.core;

import java.util.List;

/**
 * Created by Jerry Wang on 2018/11/5.
 */
public class StateChart {

    private State startState;

    private State endState;

    private List<State> stateList;

    private List<Event> eventList;

    public State getStartState() {
        return startState;
    }

    public void setStartState(State startState) {
        this.startState = startState;
    }

    public State getEndState() {
        return endState;
    }

    public void setEndState(State endState) {
        this.endState = endState;
    }

    public List<State> getStateList() {
        return stateList;
    }

    public void setStateList(List<State> stateList) {
        this.stateList = stateList;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }
}
