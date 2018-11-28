package edu.ut.verify.example;

import edu.ut.verify.ProcessController;
import edu.ut.verify.core.StateChart;
import edu.ut.verify.core.StateMachine;
import edu.ut.verify.core.TestCase;
import edu.ut.verify.core.TestcaseGenerator;
import edu.ut.verify.statechart2java.XMIPaser;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static edu.ut.verify.ProcessController.orderEvaluator;
import static edu.ut.verify.ProcessController.testCaseEvaluator;

/**
 * Created by Jerry Wang on 2018/11/28.
 */
public class VendingMachineImplTest {

    static List<Order> orders;
    Order order;
    ResultMsg resultMsg;

    @BeforeClass
    public static void initInputSet() throws Exception{
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

        orders = new ArrayList<>();

        tg.getCaseList().stream().filter(TestCase::isValid).forEach(e->{
            orders.add(new Order(e));
        });

        testCaseEvaluator(tg.getCaseList());

        System.out.println();
        System.out.println();
        System.out.println(" ********************************* After Invalid Filter *********************************** ");
        System.out.println();
        System.out.println();

        orderEvaluator(orders);

    }

    public ResultMsg test(int index){
        order = orders.get(index);
        VendingMachineService vendingMachineService = new VendingMachineImpl();
        return vendingMachineService.purchasing(order);
    }

    @After
    public void printPath(){
        System.out.println("===========================================================================================");
        System.out.println("PARAMETERS    : " + order);
        System.out.println("ACTUAL PATH   : "+resultMsg.getPathStatus());
        System.out.println("EXPECTED PATH : " + order.getSequence().toString());
    }

    @Test
    public void noInputDataTest(){
        resultMsg = test(0);
        Assert.assertEquals(resultMsg.getPathStatus().toString(),order.getSequence().toString());
    }

    @Test
    public void notEnoughMoneyTest(){
        resultMsg = test(1);
        Assert.assertEquals(resultMsg.getPathStatus().toString(),order.getSequence().toString());
;    }



}
