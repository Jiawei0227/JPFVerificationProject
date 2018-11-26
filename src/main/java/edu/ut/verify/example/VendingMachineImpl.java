package edu.ut.verify.example;

import java.util.List;

/**
 * Created by Jerry Wang on 2018/11/26.
 */
public class VendingMachineImpl implements VendingMachineService {

    ResultMsg resultMsg;

    public VendingMachineImpl(){
        resultMsg = new ResultMsg();
    }

    @Override
    public ResultMsg purchasing(Order order) {

        this.powerOn();

        if ( order == null ){
            this.powerOff();
            return this.resultMsg;
        }

        this.selectDisplay();

        // for each item select the items
        order.getItems().forEach(e-> {
            for(int i = 0 ; i < e.getNumber(); i++){
                this.showAvailableSoftDrink();
                this.selectSoftDrink();
                this.availableDrinkAfterSell();
                this.showToSelectPanel();
            }
        });

        insertMoney();
        verifyAmount();
        int total = amountCount(order.getItems());

        if(order.getInputMoney() < total){
            this.notEnoughMoney();
        }else{
            this.vendingMachineBusy();
            this.dispenseSoftdrink();
        }

        this.changeDispense();
        this.powerOff();

        //TODO service logic implementation a little bit rely on the order class input




        return this.resultMsg;
    }

    @Override
    public void powerOn() {
        resultMsg.addPath(PathStatus.POWER_SWITCH_ON);
    }

    @Override
    public void powerOff() {
        resultMsg.addPath(PathStatus.POWER_SWITCH_OFF);
    }

    @Override
    public void selectDisplay() {
        resultMsg.addPath(PathStatus.SELECTION_DISPLAY);
    }

    @Override
    public void showAvailableSoftDrink() {
        resultMsg.addPath(PathStatus.SHOW_AVAILABLE_SOFT_DRINK);
    }

    @Override
    public void selectSoftDrink() {
        resultMsg.addPath(PathStatus.SELECT_SOFT_DRINK);
    }

    @Override
    public void insertMoney() {
        resultMsg.addPath(PathStatus.INSERT_MONEY);
    }

    @Override
    public void verifyAmount() {
        resultMsg.addPath(PathStatus.VERIFY_AMOUNT);
    }

    @Override
    public int amountCount(List<Item> items) {
        resultMsg.addPath(PathStatus.AMOUNT_COUNT);
        return items.stream().mapToInt(Item::getTotalPrice).reduce((x,y)->x+y).getAsInt();
    }

    @Override
    public void showToSelectPanel() {
        resultMsg.addPath(PathStatus.SHOW_TO_SELECT_PANEL);
    }

    @Override
    public void availableDrinkAfterSell() {
        resultMsg.addPath(PathStatus.AVAILABLE_DRINK_AFTER_SELL);
    }

    @Override
    public void vendingMachineBusy() {
        resultMsg.addPath(PathStatus.VENDING_MACHINE_BUSY);
    }

    @Override
    public void notEnoughMoney() {
        resultMsg.addPath(PathStatus.NOT_ENOUGH_MONEY);
    }

    @Override
    public void dispenseSoftdrink() {
        resultMsg.addPath(PathStatus.CHANGE_DISPENSE);
    }

    @Override
    public void changeDispense() {
        resultMsg.addPath(PathStatus.CHANGE_DISPENSE);
    }


}
