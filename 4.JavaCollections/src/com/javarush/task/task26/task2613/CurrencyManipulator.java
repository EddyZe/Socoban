package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CurrencyManipulator {

    private String currencyCode; // код купюры
    private Map<Integer, Integer> denominations; // номинал и кол-во купюр


    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        this.denominations = new HashMap<>();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }


    // добавляем купюры
    public void addAmount(int denomination, int count) {
        if (this.denominations.containsKey(denomination)) {
            int result = this.denominations.get(denomination) + count;
            this.denominations.remove(denomination);
            this.denominations.put(denomination, result);
        } else
            this.denominations.put(denomination, count);
    }


    // вернуть общее количество денег
    public int getTotalAmount() {
        int result = 0;
        for (Map.Entry<Integer, Integer> m : this.denominations.entrySet()) {
            result += (m.getValue() * m.getKey());
        }
        return result;
    }

    public boolean hasMoney() {
        return !this.denominations.isEmpty();
    }
}
