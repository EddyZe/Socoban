package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

class InfoCommand implements Command{

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.info_en");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage("****************************************");
        List<String> money = getStringsMoney();
        if (money.isEmpty()) {
            ConsoleHelper.writeMessage("No money available.");
            ConsoleHelper.writeMessage("****************************************");
            return;
        }
        money.forEach(ConsoleHelper::writeMessage);
        ConsoleHelper.writeMessage("****************************************");
    }

    public List<String> getStringsMoney() {
        List<String> strings = new ArrayList<>();
        for (CurrencyManipulator currencyManipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (currencyManipulator.hasMoney()) {
                String str ="\t" + currencyManipulator.getCurrencyCode() + " - " + currencyManipulator.getTotalAmount();
                strings.add(str);
            }
        }
        return strings;
    }

}
