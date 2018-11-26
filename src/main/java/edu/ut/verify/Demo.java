package edu.ut.verify;
import edu.ut.verify.core.*;
import edu.ut.verify.core.event.CallEvent;
import edu.ut.verify.core.event.Event;
import edu.ut.verify.core.exception.NoInitialStateException;
import edu.ut.verify.statechart2java.XMIPaser;

import java.io.File;

public class Demo {
    public static void main(String[] args) throws NoInitialStateException {
        String fileName = ProcessController.class.getClassLoader().getResource("VendingMachine.xmi").getFile();
        File file = new File(fileName);
        StateChart st = XMIPaser.parser(file);
        String dataInput = ProcessController.class.getClassLoader().getResource("DataInput.txt").getFile();
        st.parseDataInput(dataInput);

//        StateMachine stateMachine = new StateMachine(st);
//        stateMachine.process();
//        stateMachine.getSequences().get(19).printTransitions();

//        System.out.println(st.getFormula());

        StateMachine stateMachine = new StateMachine(st);
        TestcaseGenerator tg = new TestcaseGenerator(stateMachine);
        tg.testGenerate();

        for(TestCase tc : tg.getCaseList()){
            System.out.println(tc.toString());
        }
    }
}
