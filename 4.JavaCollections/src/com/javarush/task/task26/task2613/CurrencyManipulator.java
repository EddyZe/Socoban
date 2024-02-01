package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {

    private final String currencyCode; // код купюры
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

    public boolean isAmountAvailable(int expectedAmount) {
        if (!hasMoney()) return false;
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> result = new TreeMap<>(new MyComparator());
        Map<Integer, Integer> tempSortedMap = new TreeMap<>(new MyComparator());
        Map<Integer, Integer> newDenominationsMap = new HashMap<>(this.denominations);
        tempSortedMap.putAll(this.denominations);
        int amount = 0;
        for (Map.Entry<Integer, Integer> m : tempSortedMap.entrySet()) {
            if (amount >= expectedAmount) break;
            int nominal = m.getKey();
            int countNominal = m.getValue();
            if (amount + nominal > expectedAmount) continue;
            int count = 0;
            for (int i = 0; i < countNominal; i++) {
                if (amount + nominal > expectedAmount)
                    break;
                amount += nominal;
                count++;
            }
            result.put(nominal, count);
            newDenominationsMap.remove(nominal);
            if (countNominal - count > 0) {
                newDenominationsMap.put(nominal, countNominal - count);
            }
        }
        if (amount == expectedAmount) {
            this.denominations.clear();
            this.denominations.putAll(newDenominationsMap);
        } else throw new NotEnoughMoneyException();
        return result;
    }

    private class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
}
