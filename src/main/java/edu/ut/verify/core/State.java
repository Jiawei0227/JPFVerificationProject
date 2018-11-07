package edu.ut.verify.core;

import edu.ut.verify.core.event.Event;

import java.util.List;

/**
 * Created by Jerry Wang on 2018/11/5.
 */
public class State {

    private String name;
    private String ID;

    private List<Event> events;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
