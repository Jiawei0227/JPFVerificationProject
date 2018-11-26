package edu.ut.verify.example;

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

        //TODO service logic implementation a little bit rely on the order class input


        this.powerOff();

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

    }

    @Override
    public void insertMoney() {

    }

    @Override
    public void verifyAmount() {

    }

    @Override
    public void amoungCount() {

    }

    @Override
    public void showToSelectPanel() {

    }

    @Override
    public void availableDrinkAfterSell() {

    }

    @Override
    public void vendingMachineBusy() {

    }

    @Override
    public void notEnoughMoney() {

    }

    @Override
    public void changeDispense() {

    }


}
