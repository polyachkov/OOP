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

    @Test void testAdd() throws LogarithmIncorrectArgumentException, RootLessThanZeroException, IllegalExpressionException, DivisionByZeroException {
        Double res  = Calculator.calculateExpression("+ 11 -45");
        Assertions.assertEquals(res, -34);
    }

    @Test void testMinus() throws LogarithmIncorrectArgumentException, RootLessThanZeroException, IllegalExpressionException, DivisionByZeroException {
        Double res  = Calculator.calculateExpression("- 11 -45");
        Assertions.assertEquals(res, 56);
    }

    @Test void testMul() throws LogarithmIncorrectArgumentException, RootLessThanZeroException, IllegalExpressionException, DivisionByZeroException {
        Double res  = Calculator.calculateExpression("* 10 -45");
        Assertions.assertEquals(res, -450);
    }

    @Test void testDiv() throws LogarithmIncorrectArgumentException, RootLessThanZeroException, IllegalExpressionException, DivisionByZeroException {
        Double res  = Calculator.calculateExpression("/ 121 11");
        Assertions.assertEquals(res, 11);
    }

    @Test void testSin() throws LogarithmIncorrectArgumentException, RootLessThanZeroException, IllegalExpressionException, DivisionByZeroException {
        Double res  = Calculator.calculateExpression("sin 0");
        Assertions.assertEquals(res, 0);
    }

    @Test void testCos() throws LogarithmIncorrectArgumentException, RootLessThanZeroException, IllegalExpressionException, DivisionByZeroException {
        Double res  = Calculator.calculateExpression("cos 0");
        Assertions.assertEquals(res, 1);
    }

    @Test void testLog() throws LogarithmIncorrectArgumentException, RootLessThanZeroException, IllegalExpressionException, DivisionByZeroException {
        Double res  = Calculator.calculateExpression("log 145");
        Assertions.assertEquals(Math.round(res * 1000), 2161);
    }

    @Test void testPow() throws LogarithmIncorrectArgumentException, RootLessThanZeroException, IllegalExpressionException, DivisionByZeroException {
        Double res  = Calculator.calculateExpression("pow 2 10");
        Assertions.assertEquals(res, 1024);
    }

    @Test void testSqrt() throws LogarithmIncorrectArgumentException, RootLessThanZeroException, IllegalExpressionException, DivisionByZeroException {
        Double res  = Calculator.calculateExpression("sqrt 1024");
        Assertions.assertEquals(res, 32);
    }


    @Test
    public void testWhiteSpace() throws LogarithmIncorrectArgumentException, RootLessThanZeroException, IllegalExpressionException, DivisionByZeroException {
        Double res = Calculator.calculateExpression("    +   9       9");
        Assertions.assertEquals(res, 18.0);
    }
}
