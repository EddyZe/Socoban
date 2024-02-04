package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command{

    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.exit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Вы действительно хотите выйти?");
        while (true) {
            try {
                ConsoleHelper.writeMessage("Введите 'y' или 'n'!");
                String result = ConsoleHelper.readString();
                if (result.equalsIgnoreCase("y")) {
                    ConsoleHelper.writeMessage("До свидания!");
                    return;
                } else if (result.equalsIgnoreCase("n")) {
                    return;
                }
            } catch (NullPointerException e) {
                ConsoleHelper.writeMessage("Введите 'y' или 'n'!");
            }
        }
    }
}
