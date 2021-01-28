package org.example;

import java.math.BigDecimal;
import java.math.MathContext;

public class CalcMethod {

    private static final String BY_ZERO_EXCEPTION_MSG = "деление на ноль невозможно";
    private static final String RESULT_IS_UNDEFINED_MSG = "результат не определен";

    public static String calculate(BigDecimal a, String operand, BigDecimal b) {
        switch (operand) {
            case "+" :
                return formatResult(add(a, b));
            case "-" :
                return formatResult(subtract(a, b));
            case "*" :
                return formatResult(multiply(a, b));
            case "/" :
                return formatResult(divide(a, b));
            default:
                return "0";
        }
    }

    private static String add(BigDecimal a, BigDecimal b) {
        return a.add(b, MathContext.DECIMAL128).toString();
    }

    private static String subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b, MathContext.DECIMAL128).toString();
    }

    private static String multiply(BigDecimal a, BigDecimal b) {
        if (Double.compare(a.doubleValue(), 0) == 0 || Double.compare(b.doubleValue(), 0) == 0) {
            return "0";
        }
        else {
            return a.multiply(b, MathContext.DECIMAL128).toString();
        }
    }

    private static String divide(BigDecimal a, BigDecimal b) {
        if (Double.compare(a.doubleValue(), 0) == 0 && Double.compare(b.doubleValue(), 0) == 0) {
            return RESULT_IS_UNDEFINED_MSG;
        }
        else if (Double.compare(a.doubleValue(), 0) == 0) {
            return "0";
        }
        else if (Double.compare(b.doubleValue(), 0) == 0) {
            return BY_ZERO_EXCEPTION_MSG;
        }
        return a.divide(b, MathContext.DECIMAL128).toString();
    }

    //Метод нужен для форматирования таких значений, как 9.0 или 7.0000 в просто 9 и 7
    private static String formatResult(String result) {
        StringBuilder builder = new StringBuilder(".0");
        for (int i = 0; i < 10; i++) {
            if (result.endsWith(builder.toString())) {
                result = result.replace(builder.toString(), "");
            }
            builder.append("0");
        }
        return result;
    }
}