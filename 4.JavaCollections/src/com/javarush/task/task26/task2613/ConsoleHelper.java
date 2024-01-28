package com.javarush.task.task26.task2613;

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

    public static String readString() {
        try {
            return bis.readLine();
        } catch (Exception e) {
        }
        return null;
    }

    public static String askCurrencyCode() {
        String result;
        do {
            writeMessage("Введите код валюты (Например: 'USD'): ");
            result = ConsoleHelper.readString();
        } while (result.length() != 3);

        return result.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) {
        int n = -1;
        int x = -1;
        do {
            writeMessage("Введите через пробел номинал и кол-во купюр: ");
            String[] temp = readString().split(" ");
            try {
                n = Integer.parseInt(temp[0]);
                x = Integer.parseInt(temp[1]);
            } catch (Exception e) {
                writeMessage("Введите только целые, положительные числа!");
            }
        } while (n <= 0 || x <= 0);
        String[] result = new String[2];
        result[0] = String.valueOf(n);
        result[1] = String.valueOf(x);
        return result;
    }
}
