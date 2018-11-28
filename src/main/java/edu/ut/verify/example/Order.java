package edu.ut.verify.example;

import edu.ut.verify.core.Sequence;
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

    private Sequence sequence;

    public Order(TestCase testCase){
        this.sequence = testCase.getSequence();
        if(testCase.getValues() == null) {
            empty = true;
            return;
        }
        this.price = testCase.getValues().getOrDefault("P",-1);
        this.number = testCase.getValues().getOrDefault("N",-1);
        this.inputMoney = testCase.getValues().getOrDefault("Amt",-1);
        this.returnMoney = testCase.getValues().getOrDefault("ReturnMoney",Integer.MIN_VALUE);

    }

    public String toString(){
        return String.format("Order: empty %b, price: %d, number: %d, inputMoney: %d, returnMoney: %d",empty,price,number,inputMoney,returnMoney);
    }

    public Sequence getSequence() {
        return sequence;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
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
