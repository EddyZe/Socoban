package com.javarush.task.task26.task2613;

import java.util.HashMap;
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
}
