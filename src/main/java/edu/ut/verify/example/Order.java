package edu.ut.verify.example;

import edu.ut.verify.core.TestCase;

/**
 * Created by Jerry Wang on 2018/11/26.
 */
public class Order {

    private int price;

    private int number;

    private int inputMoney;

    public Order(TestCase testCase){

        this.price = testCase.getValues().get("P");
        this.number = testCase.getValues().get("N");
        this.inputMoney = testCase.getValues().get("Amt");

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
