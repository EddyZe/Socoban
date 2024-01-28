package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>(); // код купюры и их номинал и кол-во купюр

    private CurrencyManipulatorFactory() {

    }

    // добавляем нужный код
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        currencyCode = currencyCode.toLowerCase(Locale.ROOT);
        if (!map.containsKey(currencyCode))
            map.put(currencyCode, new CurrencyManipulator(currencyCode));
        return map.get(currencyCode);
    }
}
