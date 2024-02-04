package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

import static com.javarush.task.task26.task2613.CashMachine.RESOURCE_PATH;

class WithdrawCommand implements Command{

    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {

        String before = res.getString("before");
        String specifyAmount = res.getString("specify.amount");

        ConsoleHelper.writeMessage("****************************************");
        ConsoleHelper.writeMessage(before);
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        ConsoleHelper.writeMessage("Enter the amount: ");
        while (true) {
            try {
                int sum = Integer.parseInt(ConsoleHelper.readString());
                if  (currencyManipulator.isAmountAvailable(sum)) {
                    Map<Integer, Integer> denominations = currencyManipulator.withdrawAmount(sum);
                    ConsoleHelper.writeMessage("------------------------");
                    for (Map.Entry<Integer,Integer> m : denominations.entrySet()) {
                        ConsoleHelper.writeMessage("\t" + m.getKey() + " - " + m.getValue());
                    }
                    ConsoleHelper.writeMessage("The operation was successful!");
                    ConsoleHelper.writeMessage("------------------------");
                    break;
                } else {
                    if (currencyManipulator.getTotalAmount() == 0) {
                        ConsoleHelper.writeMessage(String.format("%s missing", currencyManipulator.getCurrencyCode()));
                        break;
                    }
                    ConsoleHelper.writeMessage(String.format("Enter the maximum amount %d", currencyManipulator.getTotalAmount()));
                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(specifyAmount);
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage("It is impossible to issue this amount, because there are not enough banknotes");
            }
        }
        ConsoleHelper.writeMessage("****************************************");
    }
}
