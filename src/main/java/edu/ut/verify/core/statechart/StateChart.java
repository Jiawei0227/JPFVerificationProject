package edu.ut.verify.core.statechart;

import edu.ut.verify.core.dataconstraint.Formula;
import edu.ut.verify.core.dataconstraint.Order;
import edu.ut.verify.core.dataconstraint.SelfCircle;
import edu.ut.verify.core.state.EndState;
import edu.ut.verify.core.state.StartState;
import edu.ut.verify.core.state.State;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
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

    private Order orderVar;

    /**
     *String : transition name
     */
    private Map<String,SelfCircle> selfCircleMap;

    public Map<String, SelfCircle> getSelfCircleMap() {
        return selfCircleMap;
    }

    public void setSelfCircleMap(Map<String, SelfCircle> selfCircleMap) {
        this.selfCircleMap = selfCircleMap;
    }

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

    public StateChart(){
        selfCircleMap = new HashMap<>();
    }


    public Transition getTransition(String trans) {

        for (State s : stateTransitionMap.keySet()) {

            List<Transition> tranList = stateTransitionMap.get(s);

            for (int i = 0; i < tranList.size(); i ++){

                if (tranList.get(i).getEvent().getName().equals(trans))  return tranList.get(i);

            }
        }
        return null;

    }

    public void parseDataInput(String fileName) {

            File file = new File(fileName);

            boolean pred = false;
            boolean form = false;
            boolean ord = false;
            boolean selfcircle = false;

            try
            {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                String line, varName;
                String splitLine[];
                String eq[];
                Predicate curPred;
                formula = new Formula();
                orderVar = new Order();

                while ((line = br.readLine())  != null) {


                    //System.out.println(line);
                    splitLine = line.split(":");

                    if (splitLine[0].equals("Predicates")) {
                        pred = true;
                    } else if (splitLine[0].equals("Formulation")) {
                        pred = false;
                        form = true;
                    } else if (splitLine[0].equals("Order")) {
                        form = false;
                        ord = true;
                    } else if (splitLine[0].equals("SelfCircle")) {
                        selfcircle = true;
                        ord = false;
                    } else if (pred) {

                        //System.out.println(splitLine[0]);
                        Transition curTrans = getTransition(splitLine[0]);

                        eq = splitLine[1].split("\\s");

                        curPred = curTrans.getEvent().getPredicate();
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
                        /*for (int i = 0; i < splitLine.length; i++) {
                            System.out.println(splitLine[i]);
                        }*/

                        String[] templeStr = new String[3];
                        for (int i = 2; i < splitLine.length; i++) {
                            templeStr[i-2] = splitLine[i];
                        }
                        String forVar = splitLine[0];

                        formula.putFormula(forVar, templeStr);
                        //System.out.println(this.formula);
                    } else if (ord) {

                        String[] curOrd = line.split("\\s");
                        orderVar.put(curOrd);

                    } else if (selfcircle){


                        eq = splitLine[1].split("\\s");

                        SelfCircle selfCircle = selfCircleMap.getOrDefault(splitLine[0],new SelfCircle(splitLine[0],eq[0]));

                        Integer boundary = Integer.parseInt(eq[2]);

                        if (eq[1].equals("<=")){
                            // <=
                            selfCircle.setHigh(boundary);

                        } else if (eq[1].equals(">=")) {
                            // >=
                            selfCircle.setLow(boundary);

                        } else if (eq[1].equals("<")) {
                            // <
                            selfCircle.setHigh(boundary-1);

                        } else if (eq[1].equals(">")) {
                            // >
                            selfCircle.setLow(boundary+1);

                        } else if (eq[1].equals("=")) {
                            // =
                            selfCircle.setLow(boundary);
                            selfCircle.setHigh(boundary);
                        }

                        selfCircleMap.put(splitLine[0],selfCircle);


                    }

                }
                /*for (int i =0; i < orderVar.order.size(); i++) {
                    System.out.println("string: "+orderVar.order.get(i)[0]+" "+orderVar.order.get(i)[1]);
                }*/

            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void printSelfCircleMap(){
        System.out.println(selfCircleMap.size());

        selfCircleMap.entrySet().forEach(e->{
            System.out.print(e.getKey() + " : ");
            System.out.println(e.getValue());
        });
    }

    public Order getOrderVar() {
        return orderVar;
    }

    public void setOrderVar(Order orderVar) {
        this.orderVar = orderVar;
    }

}
