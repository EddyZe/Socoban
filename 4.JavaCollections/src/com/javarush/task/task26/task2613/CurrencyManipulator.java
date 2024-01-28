package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CurrencyManipulator {

    private String currencyCode;
    private Map<Integer, Integer> denominations;


    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        this.denominations = new HashMap<>();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (this.denominations.containsKey(denomination)) {
            int result = this.denominations.get(denomination) + count;
            this.denominations.remove(denomination);
            this.denominations.put(denomination, result);
        } else
            this.denominations.put(denomination, count);
    }
}
