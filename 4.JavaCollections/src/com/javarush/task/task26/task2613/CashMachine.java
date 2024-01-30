package com.javarush.task.task26.task2613;

import java.util.Arrays;
import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {

        Locale.setDefault(Locale.ENGLISH);

        String code = ConsoleHelper.askCurrencyCode();

        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);

        String[] denominationAndBanknotes = ConsoleHelper.getValidTwoDigits(code);

        int denomination = Integer.parseInt(denominationAndBanknotes[0]);
        int banknotes = Integer.parseInt(denominationAndBanknotes[1]);

        currencyManipulator.addAmount(denomination, banknotes);

        System.out.println(currencyManipulator.getTotalAmount());

        ConsoleHelper.askOperation();
    }
}
