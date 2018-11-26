package edu.ut.verify.core;

import edu.ut.verify.core.state.EndState;
import edu.ut.verify.core.state.StartState;
import edu.ut.verify.core.state.State;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
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
            boolean form = false;

            try
            {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                String line, varName;
                String splitLine[];
                String eq[];
                Predicate curPred;
                Formula formula = new Formula();

                while ((line = br.readLine())  != null) {


                    //System.out.println(line);
                    splitLine = line.split(":");

                    if (splitLine[0].equals("Predicates")) {
                        pred = true;
                    } else if (splitLine[0].equals("Formulation")) {
                        pred = false;
                        form = true;
                    } else if (pred) {


                        Transition curTrans = getTransition(splitLine[0]);

                        eq = splitLine[1].split("\\s");

                        curPred = curTrans.event.getPredicate();
                        curPred.setVariable(eq[0]);

                        Integer boundary = Integer.parseInt(eq[2]);

                        if (eq[1].equals("<=")){
                            // <=
                            curPred.setHigh(boundary);

                        } else if (eq[1].equals(">=")) {
                            // >=
                            curPred.setLow(boundary);

                        } else if (eq[1].equals("<")) {
                            // <
                            curPred.setHigh(boundary-1);

                        } else if (eq[1].equals(">")) {
                            // >
                            curPred.setLow(boundary+1);

                        } else if (eq[1].equals("=")) {
                            // =
                            curPred.setLow(boundary);
                            curPred.setHigh(boundary);
                        }

                        //System.out.println(curPred.toString());

                    } else if (form) {

                        splitLine = line.split("\\s");
                        formula.putFormula(splitLine[0], Arrays.copyOfRange(splitLine,1,splitLine.length-1));
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }


}
