package org.example;

import java.math.BigDecimal;

public class Calculator {

    public static void main(String[] args) {

        System.out.println("Введите первое число...");
        BigDecimal firstNumber = getNumber();

        System.out.println("Введите оператор: + | - | * | / ");
        String operand = getOperand();

        System.out.println("Введите второе число...");
        BigDecimal secondNumber = getNumber();

        String result = CalcMethod.calculate(firstNumber, operand, secondNumber);
        System.out.printf("Результат: %s%n", result);
    }

    private static BigDecimal getNumber() {
        String input;
        while (true) {
            input = ConsoleHelper.readLine();
            if (!Validator.validateNumber(input)) {
                System.out.println("Пожалуйста, введите валидное значение.");
                continue;
            }
            break;
        }
        return new BigDecimal(input);
    }

    private static String getOperand() {
        String input;
        while (true) {
            input = ConsoleHelper.readLine();
            if (!Validator.validateOperand(input)) {
                System.out.println("Пожалуйста, введите один из четырех операторов: + , - , * , / .");
                continue;
            }
            break;
        }
        return input;
    }
}