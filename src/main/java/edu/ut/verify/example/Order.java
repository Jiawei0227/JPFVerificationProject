package edu.ut.verify.example;

import edu.ut.verify.core.TestCase;

/**
 * Created by Jerry Wang on 2018/11/26.
 */
public class Order {

    private boolean empty;

    private int price;

    private int number;

    private int inputMoney;

    private int returnMoney;

    public Order(TestCase testCase){
        if(testCase.getValues() == null) {
            empty = true;
            return;
        }
        this.price = testCase.getValues().getOrDefault("P",-1);
        this.number = testCase.getValues().getOrDefault("N",-1);
        this.inputMoney = testCase.getValues().getOrDefault("Amt",-1);
        this.returnMoney = testCase.getValues().getOrDefault("ReturnMoney",Integer.MIN_VALUE);

    }

    public int getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(int returnMoney) {
        this.returnMoney = returnMoney;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public int getInputMoney() {
        return inputMoney;
    }

    public void setInputMoney(int inputMoney) {
        this.inputMoney = inputMoney;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
