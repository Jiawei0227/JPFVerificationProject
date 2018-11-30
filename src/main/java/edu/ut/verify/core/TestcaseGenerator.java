package edu.ut.verify.core;

import edu.ut.verify.core.exception.NoInitialStateException;

import java.util.*;

public class TestcaseGenerator {
    private List<Sequence> sequenceList;
    private Formula formula;
    private Set<String> allVariable;
    private Set<String> inputVariable;
    private List<TestCase> caseList;
    private Order order;
    private Map<String,SelfCircle> scMap; //Amt,{VerifyAmount, amt [0,24]}

    public TestcaseGenerator(StateMachine sm) throws NoInitialStateException {
        sm.process();
        this.formula = sm.getStateChart().getFormula();
        this.allVariable = sm.getVariableSet();
        this.sequenceList = sm.getSequences();
        this.caseList = new ArrayList<>();
        this.inputVariable = new HashSet<>();
        this.order = sm.getStateChart().getOrderVar();
        this.scMap= sm.getStateChart().getSelfCircleMap();
        /*
        get variables not in the formula
        */
        //System.out.println(allVariable.toString());
        for(String var : this.allVariable){
            if(!formula.getForm().containsKey(var))
                this.inputVariable.add(var);
        }

        //System.out.println(inputVariable.toString());
    }

    public void testGenerate(){
        //System.out.println(allVariable.toString());
//        for(String[] ord : this.order.getOrder()){
//            System.out.println("" + ord[0] +" "+ ord[1]);
//        }
        List<Sequence> removeList = new ArrayList<>();
        // filter path in wrong order
        for(Sequence se : sequenceList){
            int flag = 0;
            for(String[] ord : this.order.getOrder()){
                int former = se.indexOfTransition(ord[0]);
                int later = se.indexOfTransition(ord[1]);

                if(former == -1 || later ==-1){
//                    if(former != later){
//                        flag = -1;
//                        break;
//                    }
                }
                else if(former > later){
                    flag = -1;
                    break;
                }
            }

            if(flag == -1){
                removeList.add(se);
                TestCase tc = new TestCase(false,se,null);
                this.caseList.add(tc);
            }
        }

        sequenceList.removeAll(removeList);

        for(Sequence se : sequenceList){
            Map<String,Predicate> currentVars = new HashMap<>();//<Amt,Amt[0,-1000]>
            Map<String,Integer> values = new HashMap<>();// <Amt,10>
            int flag = 0;

            //find vars in current sequence
            for(Transition tran : se.getSequence()){
                if(tran.getEvent().getPredicate().getVariable() != null)
                    currentVars.put(tran.getEvent().getPredicate().getVariable(),tran.getEvent().getPredicate());
            }

            //System.out.println(currentVars.keySet().toString());

            if(currentVars.size() == 0) {
                //se.printSequence();
                TestCase tc = new TestCase(true,se,null);
                this.caseList.add(tc);
            }
            // invalid path detect
            else if(currentVars.size() != this.allVariable.size()){
                //se.printSequence();
                for(String var_cur : currentVars.keySet()){
                    if(!inputVariable.contains(var_cur)){
                        flag = -1;
                        break;
                    }
                }
                if(flag == -1){
                    //if variables may affect each other
                    TestCase tc = new TestCase(false,se,null);
                    this.caseList.add(tc);
                }
                else{
                    //if all variables do not affect each other
                    assignValue(currentVars,values);
                    TestCase tc = new TestCase(true,se,values);
                    this.caseList.add(tc);
                }

            }
            else{
                //System.out.println("values");
                for(String tran : this.scMap.keySet()){
                    if(se.indexOfTransition(tran) == -1){
                        //se.printSequence();
                        Predicate tempPre = currentVars.get(this.scMap.get(tran).getVarName());
                        tempPre.setHigh(this.scMap.get(tran).getBoundary()[1]);
                        tempPre.setLow(this.scMap.get(tran).getBoundary()[0]);
                        //System.out.println(currentVars.toString());
                    }
                }

                //System.out.println(currentVars.toString());
                assignValue(currentVars,values);

                //se.printSequence();
                while(!adjustValue(values,this.formula,this.inputVariable,currentVars)){
                    assignValue(currentVars,values);
                }

//                System.out.println(values.keySet().toString());
//                System.out.println(values.values().toString());
                if(adjustValue(values,this.formula,this.inputVariable,currentVars)){
                    TestCase tc = new TestCase(true,se,values);
                    this.caseList.add(tc);
//                    System.out.println(values.keySet().toString());
//                    System.out.println(values.values().toString());
                }
                else{
                    TestCase tc = new TestCase(false,se,values);
                    this.caseList.add(tc);
                }
            }

        }
    }

    private static boolean adjustValue(Map<String,Integer> values,Formula formula,Set<String> inputVariable,Map<String,Predicate> currentVars){
        Stack<String> process = new Stack<>();
        for(String var : formula.getForm().keySet()){
            process.push(var);
            //System.out.println(var);
        }

        while(!process.isEmpty()){
            String var = process.pop();
            //System.out.println(var);
            int left = values.get(var);
            int right = rightValue(formula.getForm().get(var),values);
            if(left != right){
                while(left != right && left<currentVars.get(var).getHigh() && left>currentVars.get(var).getLow()){
                    if(left<right)
                        left++;
                    else if(left>right)
                        left--;
                }

                if(left != right){
                    String rightVar;
                    String[] expression = formula.getForm().get(var);
                    rightVar = expression[0];
                    if (!inputVariable.contains(rightVar))
                        process.push(rightVar);
                    int rightChange = values.get(rightVar);
                    while (left != right && rightChange < currentVars.get(rightVar).getHigh() && rightChange > currentVars.get(rightVar).getLow()) {
                        if(left < right){
                            rightChange--;
                            values.put(rightVar,rightChange);
                            right = rightValue(formula.getForm().get(var),values);
                            if(left>right)
                                break;
                        }
                        else if(left>right){
                            rightChange++;
                            values.put(rightVar,rightChange);
                            right = rightValue(formula.getForm().get(var),values);
                            if(left<right)
                                break;
                        }

                    }

                    right = rightValue(formula.getForm().get(var),values);

                    if(left != right){
                        while(left != right && left<currentVars.get(var).getHigh() && left>currentVars.get(var).getLow()){
                            if(left<right)
                                left++;
                            else if(left>right)
                                left--;
                        }
                        if (left != right)
                            return false;
                        else{
                            values.put(var,left);
                            values.put(rightVar,rightChange);
                        }
                    }
                    else{
                        values.put(var,left);
                        values.put(rightVar,rightChange);
                    }
                }
                else {
                    values.put(var,left);
                }
            }
        }
        return true;
    }


    private static int rightValue(String[] expression,Map<String,Integer> values){
        if(expression == null || expression.length == 0)
            return 0;
        if(expression[1].equals("+"))
            return values.get(expression[0]) + values.get(expression[2]);
        else if(expression[1].equals("-"))
            return values.get(expression[0]) - values.get(expression[2]);
        else if(expression[1].equals("*"))
            return values.get(expression[0]) * values.get(expression[2]);
        else if(expression[1].equals("/"))
            return values.get(expression[0]) / values.get(expression[2]);

        return 0;
    }

    private static void assignValue(Map<String,Predicate> vars,Map<String,Integer> values){
        for(String var:vars.keySet()){
            int max= vars.get(var).getHigh();
            int min= vars.get(var).getLow();
            Random random = new Random();
            int s = 0;
//
//            System.out.println(max);
//            System.out.println(min);

            if(min>0)
                s =  random.nextInt(max)%(max-min+1) + min;
            else if(min == max && min ==0)
                s =0;
            else
                s = random.nextInt(max - min) + min;
            //System.out.println(s);
            values.put(var,s);
        }
    }

    public List<Sequence> getSequenceList() {
        return sequenceList;
    }

    public void setSequenceList(List<Sequence> sequenceList) {
        this.sequenceList = sequenceList;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Set<String> getAllVariable() {
        return allVariable;
    }

    public void setAllVariable(Set<String> allVariable) {
        this.allVariable = allVariable;
    }

    public Set<String> getInputVariable() {
        return inputVariable;
    }

    public void setInputVariable(Set<String> inputVariable) {
        this.inputVariable = inputVariable;
    }

    public List<TestCase> getCaseList() {
        return caseList;
    }

    public void setCaseList(List<TestCase> caseList) {
        this.caseList = caseList;
    }
}
