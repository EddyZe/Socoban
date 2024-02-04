package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class LoginCommand implements Command {
    private final ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");
    private final ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.login_en");

    String before = res.getString("before");
    String validCardAndPinLength = res.getString("try.again.with.details");
    String validCard = res.getString("not.verified.format");
    String againOrExit = res.getString("try.again.or.exit");
    String specifyData = res.getString("specify.data");
    String successFormat = res.getString("success.format");


    @Override
    public void execute() throws InterruptOperationException {

        ConsoleHelper.writeMessage("****************************************");
        ConsoleHelper.writeMessage(before);
        ConsoleHelper.writeMessage(specifyData);
        while (true) {
            try {
                System.out.print("Card: ");
                long number = Long.parseLong(ConsoleHelper.readString());
                System.out.print("Pin: ");
                int pin = Integer.parseInt(ConsoleHelper.readString());
                if (String.valueOf(pin).length() != 4 || String.valueOf(number).length() != 12) {
                    ConsoleHelper.writeMessage(validCardAndPinLength);
                    continue;
                }
                if (!validCreditCards.containsKey(String.valueOf(number))) {
                    ConsoleHelper.writeMessage(String.format(validCard, number));
                    ConsoleHelper.writeMessage(againOrExit);
                    continue;
                }
                if (String.valueOf(pin).equals(validCreditCards.getString(String.valueOf(number)))) {
                    ConsoleHelper.writeMessage(String.format(successFormat, number));
                    break;
                } else {
                    ConsoleHelper.writeMessage("Pin not valid");
                    ConsoleHelper.writeMessage(againOrExit);
                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("Enter only the numbers!");
                ConsoleHelper.writeMessage(againOrExit);
            }
        }
        ConsoleHelper.writeMessage("****************************************");
    }
}
