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
        ConsoleHelper.writeMessage("****************************************");
        ConsoleHelper.writeMessage("Withdrawing...");
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
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
                } else {
                    if (currencyManipulator.getTotalAmount() == 0) {
                        ConsoleHelper.writeMessage(String.format("%s отсуствуют", currencyManipulator.getCurrencyCode()));
                        break;
                    }
                    ConsoleHelper.writeMessage(String.format("Введите сумму не более %d", currencyManipulator.getTotalAmount()));
                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("Введите целое число!");
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage("Невозможно выдать данную сумму, т.к не хватает банкнот");
            }
        }
        ConsoleHelper.writeMessage("****************************************");
    }
}
