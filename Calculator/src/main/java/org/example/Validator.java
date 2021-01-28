package org.example;

public class Validator {

    public static boolean validateOperand(String operand) {
        return (operand.equals("+") || operand.equals("-") || operand.equals("*") || operand.equals("/"));
    }

    public static boolean validateNumber(String number) {
        return !number.isEmpty() && number.matches("[-+]?[\\d]*\\.?([\\d]+([eE][-+]?[\\d]{1,9})?)?");
    }
}
