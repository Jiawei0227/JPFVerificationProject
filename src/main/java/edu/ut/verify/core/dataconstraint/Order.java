package edu.ut.verify.core.dataconstraint;

import java.util.ArrayList;

public class Order {
    ArrayList<String[]> order;

    public Order(){
        this.order = new ArrayList<>();
    }

    public void put(String[] ord){
        this.order.add(ord);
    }

    public ArrayList<String[]> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<String[]> order) {
        this.order = order;
    }
}
