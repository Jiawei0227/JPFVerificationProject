package edu.ut.verify.core.state;

/**
 * Created by Jerry Wang on 2018/11/5.
 */
public abstract class State {

    private String name;

    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public State(){}

    public State(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
