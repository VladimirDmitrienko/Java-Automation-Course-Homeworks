package org.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;


public class CalcMethodTest {

    private static Stream<Arguments> generateNumbersForAddition() {
        return Stream.of(
                Arguments.of("2", "3", "5"),
                Arguments.of("3.5", "4.3", "7.8"),
                Arguments.of("2E+100", "2E+100", "4E+100")
        );
    }

    private static Stream<Arguments> generateNumbersForSubtraction() {
        return Stream.of(
                Arguments.of("10", "35", "-25"),
                Arguments.of("66.5", "33.4", "33.1"),
                Arguments.of("5E+100", "2E+100", "3E+100")
        );
    }

    private static Stream<Arguments> generateNumbersForMultiplication() {
        return Stream.of(
                Arguments.of("10000", "-1", "-10000"),
                Arguments.of("333.6", "2.763", "921.7368"),
                Arguments.of("2E+100", "3E+100", "6E+200")
        );
    }

    private static Stream<Arguments> generateNumbersForDivision() {
        return Stream.of(
                Arguments.of("1000", "50", "20"),
                Arguments.of("2.36", "1.18", "2"),
                Arguments.of("10", "0", "деление на ноль невозможно"),
                Arguments.of("0", "0", "результат не определен"),
                Arguments.of("8E+200", "4E+100", "2E+100")
        );
    }

    @ParameterizedTest
    @MethodSource("generateNumbersForAddition")
    public void testAddition(String a, String b, String expectedResult) {
        BigDecimal firstNumber = new BigDecimal(a);
        BigDecimal secondNumber = new BigDecimal(b);
        String result = CalcMethod.calculate(firstNumber, "+", secondNumber);
        Assertions.assertEquals(result, expectedResult);
    }

    @ParameterizedTest
    @MethodSource("generateNumbersForSubtraction")
    public void testSubtraction(String a, String b, String expectedResult) {
        BigDecimal firstNumber = new BigDecimal(a);
        BigDecimal secondNumber = new BigDecimal(b);
        String result = CalcMethod.calculate(firstNumber, "-", secondNumber);
        Assertions.assertEquals(result, expectedResult);
    }

    @ParameterizedTest
    @MethodSource("generateNumbersForMultiplication")
    public void testMultiplication(String a, String b, String expectedResult) {
        BigDecimal firstNumber = new BigDecimal(a);
        BigDecimal secondNumber = new BigDecimal(b);
        String result = CalcMethod.calculate(firstNumber, "*", secondNumber);
        Assertions.assertEquals(result, expectedResult);
    }

    @ParameterizedTest
    @MethodSource("generateNumbersForDivision")
    public void testDivision(String a, String b, String expectedResult) {
        BigDecimal firstNumber = new BigDecimal(a);
        BigDecimal secondNumber = new BigDecimal(b);
        String result = CalcMethod.calculate(firstNumber, "/", secondNumber);
        Assertions.assertEquals(result, expectedResult);
    }
}