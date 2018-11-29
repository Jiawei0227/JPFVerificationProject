package edu.ut.verify.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry Wang on 2018/11/26.
 */
public class PathStatus {

    private List<String> pathCoverage;

    public PathStatus(){
        this.pathCoverage = new ArrayList<>();
    }

    public List<String> getPathCoverage() {
        return pathCoverage;
    }

    public String toString(){
        return String.join(" -> ", pathCoverage);
    }

    public boolean addPath(String path){
        return this.pathCoverage.add(path);
    }

    public void setPathCoverage(List<String> pathCoverage) {
        this.pathCoverage = pathCoverage;
    }

    public static final String POWER_SWITCH_ON = "PowerSwitchOn";

    public static final String NOT_ENOUGH_DRINK = "NotEnoughSoftdrink";

    public static final String POWER_SWITCH_OFF = "PowerSwitchOff";

    public static final String SELECTION_DISPLAY = "SelectionDisplay";

    public static final String SHOW_AVAILABLE_SOFT_DRINK = "ShowAvailableSoftDrink";

    public static final String SELECT_SOFT_DRINK = "SelectSoftdrink";

    public static final String INSERT_MONEY = "InsertMoney";

    public static final String SHOW_TO_SELECT_PANEL = "ShowToSelectPanel";

    public static final String AVAILABLE_DRINK_AFTER_SELL = "AvailableDrinkAfterSell";

    public static final String VERIFY_AMOUNT = "VerifyAmount";

    public static final String AMOUNT_COUNT = "AmountCount";

    public static final String DISPENSE_SOFTDRINK = "DispenseSoftDrink";

    public static final String VENDING_MACHINE_BUSY = "VendingMachineBusy";

    public static final String NOT_ENOUGH_MONEY = "NotEnoughMoney";

    public static final String CHANGE_DISPENSE = "ChangeDispense";

    public static final String NO_CHANGE_DISPENSE = "NoChangeDispense";



}
