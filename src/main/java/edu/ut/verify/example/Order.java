package edu.ut.verify.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry Wang on 2018/11/26.
 */
public class Order {

    private List<Item> items;

    private int inputMoney;

    public Order(){
        this.items = new ArrayList<>();
    }


    public int getInputMoney() {
        return inputMoney;
    }

    public void setInputMoney(int inputMoney) {
        this.inputMoney = inputMoney;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item){
        this.items.add(item);
    }

}
