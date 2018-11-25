package edu.ut.verify.core;

import edu.ut.verify.core.exception.NoInitialStateException;

import java.util.List;

public class TestcaseGenerator {
    private StateMachine stateMachine;
    private List<Sequence> sequenceList;

    public TestcaseGenerator(StateMachine sm){
        this.stateMachine = sm;
    }

    public void testGenerate() throws NoInitialStateException {
        stateMachine.process();
        sequenceList = stateMachine.getSequences();

        for(int i =0; i<sequenceList.size();i++){
            sequenceList.get(i).printTransitions();
        }
    }
}
