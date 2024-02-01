package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

class WithdrawCommand implements Command{
    @Override
    public void execute() throws InterruptOperationException {
        CurrencyManipulator currencyManipulator = null;
        while (currencyManipulator == null) {
            ConsoleHelper.writeMessage("Введите код валюты (Например: USD): ");
            String code = ConsoleHelper.readString();
            Collection<CurrencyManipulator> currencyManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();
            for (CurrencyManipulator cm : currencyManipulators) {
                if (cm.getCurrencyCode().equalsIgnoreCase(code)) {
                    currencyManipulator = cm;
                }
            }
        }
        ConsoleHelper.writeMessage("Введите сумму: ");
        while (true) {
            try {
                int sum = Integer.parseInt(ConsoleHelper.readString());
                if  (currencyManipulator.isAmountAvailable(sum)) {
                    Map<Integer, Integer> denominations = currencyManipulator.withdrawAmount(sum);
                    ConsoleHelper.writeMessage("------------------------");
                    for (Map.Entry<Integer,Integer> m : denominations.entrySet()) {
                        ConsoleHelper.writeMessage("\t" + m.getKey() + " - " + m.getValue());
                    }
                    ConsoleHelper.writeMessage("Операция прошла успешно!");
                    ConsoleHelper.writeMessage("------------------------");
                    break;
                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("Введите целое число!");
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage("Невозможно выдать данную сумму, т.к не хватает банкнот");
            }
        }
    }
}
