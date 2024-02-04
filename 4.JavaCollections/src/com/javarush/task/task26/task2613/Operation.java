package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

public enum Operation {
    LOGIN,
    INFO,
    DEPOSIT,
    WITHDRAW,
    EXIT;


    public static Operation getAllowableOperationByOrdinal(Integer i) {
        try {
            return Operation.values()[i];
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
