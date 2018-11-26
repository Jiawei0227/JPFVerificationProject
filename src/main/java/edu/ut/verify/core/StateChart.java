package edu.ut.verify.core;

import edu.ut.verify.core.state.EndState;
import edu.ut.verify.core.state.StartState;
import edu.ut.verify.core.state.State;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerry Wang on 2018/11/5.
 */
public class StateChart {

    private StartState startState;

    private EndState endState;

    private Map<State, List<Transition>> stateTransitionMap;

    private Formula formula; //used to store relationship between variable

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public StartState getStartState() {
        return startState;
    }

    public void setStartState(StartState startState) {
        this.startState = startState;
    }

    public EndState getEndState() { return endState; }

    public void setEndState(EndState endState) {
        this.endState = endState;
    }

    public Map<State, List<Transition>> getStateTransitionMap() {
        return stateTransitionMap;
    }

    public void setStateTransitionMap(Map<State, List<Transition>> stateTransitionMap) {
        this.stateTransitionMap = stateTransitionMap;
    }


    public Transition getTransition(String trans) {

        for (State s : stateTransitionMap.keySet()) {

            List<Transition> tranList = stateTransitionMap.get(s);

            for (int i = 0; i < tranList.size(); i ++){

                if (tranList.get(i).event.getName().equals(trans))  return tranList.get(i);

            }
        }
        return null;

    }

    public void parseDataInput(String fileName) {

            File file = new File(fileName);

            boolean pred = false;
            boolean formula = false;

            try
            {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                String line;

                while ((line = br.readLine())  != null) {


                    System.out.println(line);
                    String splitLine[] = line.split(":");
                    System.out.println(splitLine.length);

                    if (splitLine[0].equals("Predicates")) {
                        pred = true;
                    } else if (splitLine[0].equals("Formulation")) {
                        pred = false;
                        formula = true;
                    } else if (pred) {


                            //if (splitLine.length > 1) System.out.println(splitLine[1]);

                        Transition curTrans = getTransition(splitLine[0]);
                        System.out.println(curTrans.event.getName());
                        /*Predicate curPred = curTrans.event.getPredicate();
                        if (curPred == null) {
                            curPred = new Predicate();

                        }*/
                    } else if (formula) {



                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }


}
