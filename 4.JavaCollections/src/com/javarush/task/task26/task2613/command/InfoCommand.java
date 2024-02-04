package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

class InfoCommand implements Command{

    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "info_en");

    @Override
    public void execute() {
        String before = res.getString("before");
        String noMoney = res.getString("no.money");
        ConsoleHelper.writeMessage("****************************************");
        ConsoleHelper.writeMessage(before);
        List<String> money = getStringsMoney();
        if (money.isEmpty()) {
            ConsoleHelper.writeMessage(noMoney);
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
