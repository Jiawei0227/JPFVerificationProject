package edu.ut.verify;
import edu.ut.verify.core.*;
import edu.ut.verify.core.event.CallEvent;
import edu.ut.verify.core.event.Event;
import edu.ut.verify.core.exception.NoInitialStateException;
import edu.ut.verify.statechart2java.XMIPaser;

import java.io.File;

public class Demo {
    public static void main(String[] args) throws NoInitialStateException {
        Transition tr = new Transition();
        Event ev = new CallEvent();
        Predicate pr = new Predicate("Var 1");
        pr.setLow(0);
        pr.setHigh(10);


        ev.setName("test 1");
        ev.setPredicate(pr);
        tr.setEvent(ev);

        System.out.println(tr.getEvent().toString());
    }
}
