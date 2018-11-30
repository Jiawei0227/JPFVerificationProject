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

        if ( order.isEmpty() ){
            this.powerOff();
            return this.resultMsg;
        }

        this.selectDisplay();
        this.showAvailableSoftDrink();
        if(order.getNumber() > 10){
            this.notEnoughDrink();
            this.powerOff();
            return resultMsg;
        }else{
            this.selectSoftDrink();
        }

        if(order.getNumber() >= 5) {
            availableDrinkAfterSell();
            showToSelectPanel();
        }

        insertMoney();
        int total = amountCount(order);

        if(order.getInputMoney() >= 25)
            verifyAmount();


        if(order.getInputMoney() < total){
            this.notEnoughMoney();
            this.changeDispense(order);
        }else{
            this.vendingMachineBusy();

            if(order.getInputMoney() == total){
                this.noChangeDispense();
            }else{
                this.dispenseSoftdrink();
                this.changeDispense(order);
            }

        }

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
    public int amountCount(Order order) {
        resultMsg.addPath(PathStatus.AMOUNT_COUNT);
        return order.getNumber() * order.getPrice();
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
        resultMsg.addPath(PathStatus.DISPENSE_SOFTDRINK);
    }

    @Override
    public void changeDispense(Order order) {
        resultMsg.addPath(PathStatus.CHANGE_DISPENSE);
        int reMoney = order.getInputMoney()-order.getNumber()*order.getPrice();
        if(reMoney < 0)
            resultMsg.setReturnMoney(order.getInputMoney());
        else
            resultMsg.setReturnMoney(reMoney);
    }

    @Override
    public void notEnoughDrink() {
        resultMsg.addPath(PathStatus.NOT_ENOUGH_DRINK);
    }

    @Override
    public void noChangeDispense() {
        resultMsg.addPath(PathStatus.NO_CHANGE_DISPENSE);
    }


}
