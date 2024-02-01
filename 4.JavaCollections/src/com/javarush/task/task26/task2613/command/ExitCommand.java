package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class ExitCommand implements Command{
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
