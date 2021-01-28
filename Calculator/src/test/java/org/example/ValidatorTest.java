package org.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

    @Test
    public void testValidOperators() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(Validator.validateOperand("+")),
                () -> Assertions.assertTrue(Validator.validateOperand("-")),
                () -> Assertions.assertTrue(Validator.validateOperand("*")),
                () -> Assertions.assertTrue(Validator.validateOperand("/"))
        );
    }

    @Test
    public void testInvalidOperators() {
        Assertions.assertAll(
                () -> Assertions.assertFalse(Validator.validateOperand("++")),
                () -> Assertions.assertFalse(Validator.validateOperand("%")),
                () -> Assertions.assertFalse(Validator.validateOperand("1")),
                () -> Assertions.assertFalse(Validator.validateOperand("=")),
                () -> Assertions.assertFalse(Validator.validateOperand("A"))
        );
    }

    @Test
    public void testValidNumbers() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(Validator.validateNumber("8")),
                () -> Assertions.assertTrue(Validator.validateNumber("+20000")),
                () -> Assertions.assertTrue(Validator.validateNumber("5.55")),
                () -> Assertions.assertTrue(Validator.validateNumber("+314.99999")),
                () -> Assertions.assertTrue(Validator.validateNumber("-0.11")),
                () -> Assertions.assertTrue(Validator.validateNumber("1.")),
                () -> Assertions.assertTrue(Validator.validateNumber(".98")),
                () -> Assertions.assertTrue(Validator.validateNumber("1e+1")),
                () -> Assertions.assertTrue(Validator.validateNumber("1E+999")),
                () -> Assertions.assertTrue(Validator.validateNumber("6.67e-90")),
                () -> Assertions.assertTrue(Validator.validateNumber("0.99E-1000"))
        );
    }

    @Test
    public void testInvalidNumbers() {
        Assertions.assertAll(
                () -> Assertions.assertFalse(Validator.validateNumber("")),
                () -> Assertions.assertFalse(Validator.validateNumber(" ")),
                () -> Assertions.assertFalse(Validator.validateNumber("Infinity")),
                () -> Assertions.assertFalse(Validator.validateNumber("NaN")),
                () -> Assertions.assertFalse(Validator.validateNumber("0.1.2")),
                () -> Assertions.assertFalse(Validator.validateNumber("1E+9999999999"))
        );
    }
}