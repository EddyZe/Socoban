package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command{

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        String before = res.getString("before");
        String som = res.getString("success.format");
        String invalid = res.getString("invalid.data");
        ConsoleHelper.writeMessage("****************************************");
        ConsoleHelper.writeMessage(before);
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        String[] denominationAndBanknotes = ConsoleHelper.getValidTwoDigits(code);
        int denomination = Integer.parseInt(denominationAndBanknotes[0]);
        int banknotes = Integer.parseInt(denominationAndBanknotes[1]);
        if (denomination < 0 && banknotes < 0) {
            ConsoleHelper.writeMessage(invalid);
            ConsoleHelper.writeMessage("****************************************");
            return;
        }
        currencyManipulator.addAmount(denomination, banknotes);
        ConsoleHelper.writeMessage("------------------------");
        ConsoleHelper.writeMessage(String.format(som, (denomination * banknotes), code));
        ConsoleHelper.writeMessage("------------------------");
        ConsoleHelper.writeMessage("****************************************");
    }

}
