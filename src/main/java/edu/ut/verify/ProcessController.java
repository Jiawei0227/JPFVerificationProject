package edu.ut.verify;

import edu.ut.verify.core.StateChart;
import edu.ut.verify.core.StateMachine;
import edu.ut.verify.core.TestCase;
import edu.ut.verify.core.TestcaseGenerator;
import edu.ut.verify.example.Order;
import edu.ut.verify.example.ResultMsg;
import edu.ut.verify.example.VendingMachineImpl;
import edu.ut.verify.example.VendingMachineService;
import edu.ut.verify.statechart2java.XMIPaser;

import java.io.File;
import java.util.List;

/**
 * Created by Jerry Wang on 2018/11/5.
 */
public class ProcessController {

    public static void main(String[] args) throws Exception{

        // parse file
        String fileName = ProcessController.class.getClassLoader().getResource("VendingMachine3.xmi").getFile();
        File file = new File(fileName);
        StateChart st = XMIPaser.parser(file);
        String dataInput = ProcessController.class.getClassLoader().getResource("DataInput.txt").getFile();
        st.parseDataInput(dataInput);


        // generate StateMachine Path
        StateMachine stateMachine = new StateMachine(st);
        TestcaseGenerator tg = new TestcaseGenerator(stateMachine);
        tg.testGenerate();


        // testcase generate
        testCaseEvaluator(tg.getCaseList());

    }

    public static void testCaseEvaluator(List<TestCase> testCases){

//        int number = 0;
//        for(TestCase testCase : testCases){
//
//            System.out.println("----------------------------------------------");
//            System.out.print(String.format("Path %2d : ",number+1));
//            testCase.getSequence().printSequence();
//
//            System.out.println("Parameter : " + testCase.toString());
//
//
//            System.out.println("----------------------------------------------");
//            number++;
//        }

        testCases.stream().filter(TestCase::isValid).forEach(e->{
            System.out.println("--------------------------------");

            System.out.println("Variables : "+e.toString());

            System.out.print("Transactions : ");
            e.getSequence().printSequence();

            Order order = new Order(e);
            VendingMachineService vendingMachineService = new VendingMachineImpl();
            ResultMsg re = vendingMachineService.purchasing(order);
            System.out.println("Actual Path  : "+re.getPathStatus().getPathCoverage().toString());
            System.out.println("--------------------------------");
        });
    }
}
