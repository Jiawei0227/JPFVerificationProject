package edu.ut.verify;

import edu.ut.verify.core.statechart.StateChart;
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
        String fileName = ProcessController.class.getClassLoader().getResource("VendingMachine.xmi").getFile();
        File file = new File(fileName);
        StateChart st = XMIPaser.parser(file);
        String dataInput = ProcessController.class.getClassLoader().getResource("DataInput.txt").getFile();
        st.parseDataInput(dataInput);


        // generate StateMachine Path
        StateMachine stateMachine = new StateMachine(st);
        TestcaseGenerator tg = new TestcaseGenerator(stateMachine);
        tg.testGenerate();
        //System.out.println(tg.getCaseList().get(1).toString());


        // testcase generate
        testCaseEvaluator(tg.getCaseList());

    }

    public static void orderEvaluator(List<Order> orders){
        for(int i = 0 ; i < orders.size(); i++){
            System.out.println("Valid Path "+(i));
            orders.get(i).getSequence().printSequence();
            System.out.println(orders.get(i));
            System.out.println();
        }
    }

    public static void testCaseEvaluator(List<TestCase> testCases){

        System.out.println(testCases.size());
        int number = 0;
        for(TestCase testCase : testCases){

            System.out.println("--------------------"+String.format("Path %2d : ",number+1)+"----------------------");
            System.out.println("Parameter   :" + testCase.toString());

            System.out.print("Sequences   : ");
            testCase.getSequence().printSequence();

            if(testCase.isValid()) {

                Order order = new Order(testCase);
                VendingMachineService vendingMachineService = new VendingMachineImpl();
                ResultMsg re = vendingMachineService.purchasing(order);
                System.out.println("Actual Path : " + re.getPathStatus().getPathCoverage().toString());

                if(testCase.getValues() != null) {
                    int actualReturnMoney = re.getReturnMoney();
                    int inputReturnMoney = order.getReturnMoney() == -1? order.getInputMoney():order.getReturnMoney();

                    System.out.println("Actual ReturnMoney :"+actualReturnMoney + " "+ (actualReturnMoney==inputReturnMoney?"==":"!=") +" Input ReturnMoney :" + inputReturnMoney);
                }
            }

            System.out.println("----------------------------------------------------");
            number++;
        }


    }
}
