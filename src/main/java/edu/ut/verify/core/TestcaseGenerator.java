package edu.ut.verify.core;

import edu.ut.verify.core.exception.NoInitialStateException;

import java.util.*;

public class TestcaseGenerator {
    private List<Sequence> sequenceList;
    private Formula formula;
    private Set<String> allVariable;
    private Set<String> inputVariable;
    private List<TestCase> caseList;

    public TestcaseGenerator(StateMachine sm) throws NoInitialStateException {
        sm.process();
        this.formula = sm.getStateChart().getFormula();
        this.allVariable = sm.getVariableSet();
        this.sequenceList = sm.getSequences();
        this.caseList = new ArrayList<>();
        /*
        get variables not in the formula
        */
        for(String var : this.allVariable){
            if(!formula.getForm().containsKey(var))
                this.inputVariable.add(var);
        }
    }

    public void testGenerate(){
        for(Sequence se : sequenceList){
            Map<String,Predicate> currentVars = new HashMap<>();//<Amt,Amt[0,-1000]>
            Map<String,Integer> values = new HashMap<>();// <Amt,10>

            //find vars in current sequence
            for(Transition tran : se.getSequence()){
                if(tran.getEvent().getPredicate() != null)
                    currentVars.put(tran.getEvent().getPredicate().getVariable(),tran.getEvent().getPredicate());
            }

            if(currentVars.size() == 0) {
                se.printSequence();
                TestCase tc = new TestCase(true,se,null);
                this.caseList.add(tc);
            }
            else if(currentVars.size() != this.allVariable.size()){
                se.printSequence();
                TestCase tc = new TestCase(false,se,null);
                this.caseList.add(tc);
            }
            else{

            }

        }
//        for(int i =0; i<sequenceList.size();i++){
//            sequenceList.get(i).printTransitions();
//        }
    }
}
