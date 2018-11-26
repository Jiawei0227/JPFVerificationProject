package edu.ut.verify;

import edu.ut.verify.core.StateChart;
import edu.ut.verify.core.StateMachine;
import edu.ut.verify.statechart2java.XMIPaser;

import java.io.File;

/**
 * Created by Jerry Wang on 2018/11/5.
 */
public class ProcessController {

    public static void main(String[] args) throws Exception{
        String XMI = ProcessController.class.getClassLoader().getResource("VendingMachine.xmi").getFile();
        File file = new File(XMI);
        StateChart st = XMIPaser.parser(file);
        String dataInput = ProcessController.class.getClassLoader().getResource("DataInput.txt").getFile();
        st.parseDataInput(dataInput);


        StateMachine stateMachine = new StateMachine(st);
        stateMachine.process();
        stateMachine.printSequence();

    }
}
