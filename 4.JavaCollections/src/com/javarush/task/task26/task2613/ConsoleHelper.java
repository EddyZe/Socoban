package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class ConsoleHelper {

    private static final BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

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
            if (result.contains(" ")) writeMessage("Введите код без пробелов!");
            else break;
        } while (true);
        return result.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        int n = -1;
        int x = -1;
        writeMessage("Введите через пробел номинал и кол-во купюр: ");
        do {
            String[] temp = readString().split(" ");
            if (temp.length != 2) writeMessage("Введите 2 целых числа через пробел!");
            else {
                try {
                    n = Integer.parseInt(temp[0]);
                    x = Integer.parseInt(temp[1]);
                    if (n < 0 || x < 0) writeMessage("Введите положительное число!");
                    else break;
                } catch (NumberFormatException e) {
                    writeMessage("Введите только целые, положительные числа!");
                }
            }
        } while (true);
        String[] result = new String[2];
        result[0] = String.valueOf(n);
        result[1] = String.valueOf(x);
        return result;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage("Какую операцию вы хотите выполнить:\n" +
                "1 - INFO\n" +
                "2 - DEPOSIT\n" +
                "3 - WITHDRAW\n" +
                "4 - EXIT");
        do {
            int i;
            try {
                String str = readString();
                i = Integer.parseInt(str.replaceAll(" ", ""));
                if (i < 1 || i > Operation.values().length)
                    writeMessage(String.format("Введите число от 1 до %d",
                            Operation.values().length -1));
                else
                    return Operation.getAllowableOperationByOrdinal(i);
            } catch (NumberFormatException e) {
                writeMessage("Введите число!");
            }
        } while (true);
    }
}
