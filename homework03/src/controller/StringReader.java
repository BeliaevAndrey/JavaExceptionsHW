package controller;

import view.Display;

import java.util.Scanner;

public class StringReader {

    String getString() {
        String inputString = "";
        boolean flag = true;

        Display.showPrompt("Введите, пожалуйста, данные.");

        while (flag) {

            inputString = new Scanner(System.in).nextLine();

            if (inputString.strip().equalsIgnoreCase("exit")){
                return inputString.strip();
            }

            int validCode = validate(inputString);

            if (validCode == 0) {
                flag = false;
            } else if (validCode == 6) {
                Display.showPrompt("Строка пуста. Повторите ввод, пожалуйста.");
            } else if (validCode < 0) {
                Display.showPrompt("Слишком много параметров. Повторите ввод, пожалуйста.");
            } else if (validCode > 6) {
                Display.printText("Ошибка ввода: null");
            } else {
                Display.showPrompt("Слишком мало параметров. Повторите ввод, пожалуйста.");
            }
        }
        return inputString;
    }

    private int validate(String cardString) {
        if (cardString == null) {
            return Integer.MAX_VALUE;
        }
        if (cardString.isEmpty() || cardString.isBlank()) {
            return 6;
        }
        return 6 - cardString.
                strip().
                replaceAll(" +", " ").
                split(" ").length;
    }

}
