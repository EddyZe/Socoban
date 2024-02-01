package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class DepositCommand implements Command{

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Depositing... ");
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        String[] denominationAndBanknotes = ConsoleHelper.getValidTwoDigits(code);
        int denomination = Integer.parseInt(denominationAndBanknotes[0]);
        int banknotes = Integer.parseInt(denominationAndBanknotes[1]);
        currencyManipulator.addAmount(denomination, banknotes);
        ConsoleHelper.writeMessage(String.format("Вы внесли: %d %s", (denomination * banknotes), code));
    }

}
