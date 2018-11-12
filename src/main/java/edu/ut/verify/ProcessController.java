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
        String fileName = ProcessController.class.getClassLoader().getResource("test.xmi").getFile();
        File file = new File(fileName);
        StateChart st = XMIPaser.parser(file);

        StateMachine stateMachine = new StateMachine(st);
        stateMachine.process();
        stateMachine.printSequence();

    }
}
