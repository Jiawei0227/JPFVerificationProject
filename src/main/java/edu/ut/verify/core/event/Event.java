package edu.ut.verify.core.event;

import edu.ut.verify.core.Predicate;

/**
 * Created by Jerry Wang on 2018/11/5.
 */
public abstract class Event {

    private String name;

    private String Id;

    private Predicate predicate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) { this.Id = Id; }

    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    public String toString(){
        return ""+name+": "+predicate.toString();
    }

}
