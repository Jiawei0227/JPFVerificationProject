package edu.ut.verify.core;

import java.util.List;

/**
 * Created by Jerry Wang on 2018/11/5.
 */
public class State {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
