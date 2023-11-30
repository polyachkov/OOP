package org.example;

import org.example.exceptions.DivisionByZeroException;
import org.example.exceptions.IllegalExpressionException;
import org.example.exceptions.LogarithmIncorrectArgumentException;
import org.example.exceptions.RootLessThanZeroException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    public void testFunctions() throws LogarithmIncorrectArgumentException, RootLessThanZeroException, IllegalExpressionException, DivisionByZeroException {
        Double res = Calculator.calculateExpression("sin cos sqrt log + 1 - 12 * 3 / 4 5");
        Assertions.assertEquals(Math.round(res * 1000), 505);
    }

    @Test
    public void testWhiteSpace() throws LogarithmIncorrectArgumentException, RootLessThanZeroException, IllegalExpressionException, DivisionByZeroException {
        Double res = Calculator.calculateExpression("    +   9       9");
        Assertions.assertEquals(res, 18.0);
    }
}
