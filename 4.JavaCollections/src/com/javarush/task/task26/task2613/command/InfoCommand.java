package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class InfoCommand implements Command{

    @Override
    public void execute() {
        List<String> money = getStringsMoney();
        if (money.isEmpty()) {
            ConsoleHelper.writeMessage("No money available.");
            return;
        }
        money.forEach((i) -> {
            ConsoleHelper.writeMessage("------------------");
            ConsoleHelper.writeMessage(i);
            ConsoleHelper.writeMessage("------------------");
        });
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
