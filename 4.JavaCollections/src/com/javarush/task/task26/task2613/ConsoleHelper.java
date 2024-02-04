package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConsoleHelper {

    private static final BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.common_en");

    static String theEnd = res.getString("the.end");
    static String chooseOperation = res.getString("choose.operation");
    static String operationINFO = res.getString("operation.INFO");
    static String operationDEPOSIT = res.getString("operation.DEPOSIT");
    static String operationWITHDRAW = res.getString("operation.WITHDRAW");
    static String operationEXIT = res.getString("operation.EXIT");
    static String invalidData = res.getString("invalid.data");
    static String currencyCode = res.getString("choose.currency.code");
    static String chooseDenomination = res.getString("choose.denomination.and.count.format");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String result = bis.readLine();
            if (result.equalsIgnoreCase("exit"))
                throw new InterruptOperationException();
            return result;
        } catch (IOException e) {
        }
        return null;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String result;
        do {
            writeMessage("Введите код валюты (Например: 'USD'): ");
            result = ConsoleHelper.readString().trim();
            if (result.contains(" ")) writeMessage("Enter the code without spaces!");
            else break;
        } while (true);
        return result.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        int n;
        int x;
        writeMessage(chooseDenomination);
        do {
            String[] temp = Objects.requireNonNull(readString()).split(" ");
            if (temp.length != 2) writeMessage("Enter 2 integers separated by a space!");
            else {
                try {
                    n = Integer.parseInt(temp[0]);
                    x = Integer.parseInt(temp[1]);
                    if (n < 0 || x < 0) writeMessage("Enter a positive number!");
                    else break;
                } catch (NumberFormatException e) {
                    writeMessage("Enter only integers, positive numbers!");
                }
            }
        } while (true);
        String[] result = new String[2];
        result[0] = String.valueOf(n);
        result[1] = String.valueOf(x);
        return result;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(chooseOperation + "\n" +
                "1 - " + operationINFO + "\n" +
                "2 - " + operationDEPOSIT + "\n" +
                "3 - " + operationWITHDRAW + "\n" +
                "4 - " + operationEXIT);
        do {
            int i;
            try {
                String str = readString();
                assert str != null;
                i = Integer.parseInt(str.replaceAll(" ", ""));
                if (i < 1 || i > Operation.values().length)
                    writeMessage(String.format("Enter a number from 1 to %d",
                            Operation.values().length -1));
                else
                    return Operation.getAllowableOperationByOrdinal(i);
            } catch (NumberFormatException e) {
                writeMessage("Enter a number!");
            }
        } while (true);
    }
}
