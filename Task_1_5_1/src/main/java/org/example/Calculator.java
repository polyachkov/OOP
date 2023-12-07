package org.example;

import org.example.exceptions.DivisionByZeroException;
import org.example.exceptions.IllegalExpressionException;
import org.example.exceptions.LogarithmIncorrectArgumentException;
import org.example.exceptions.RootLessThanZeroException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    public static String[] operations = new String[] {"+", "-", "*", "/", "log", "pow", "sqrt", "sin", "cos"};

    public static Double calculateExpression(String expr) throws
            DivisionByZeroException,
            IllegalExpressionException,
            LogarithmIncorrectArgumentException,
            RootLessThanZeroException {

        String[] expressionArr = expr.split(" ");
        Stack<Double> stackNum = new Stack<>();
        Double a1;
        Double a2;
        for (int i = expressionArr.length - 1; i >= 0; i--) {
            try {
                Double tmp = Double.parseDouble(expressionArr[i]);
                stackNum.push(tmp);
            }
            catch (NumberFormatException e) {
                if (Arrays.asList(operations).contains(expressionArr[i])){
                    switch (expressionArr[i]) {
                        case("+"):
                            if (stackNum.size() < 2) {
                                throw new IllegalExpressionException("Недостаточно аргументов для функции (+)");
                            }
                            a1 = stackNum.pop();
                            a2 = stackNum.pop();
                            stackNum.push(a1 + a2);
                            break;
                        case("-"):
                            if (stackNum.size() < 2) {
                                throw new IllegalExpressionException("Недостаточно аргументов для функции (-)");
                            }
                            a1 = stackNum.pop();
                            a2 = stackNum.pop();
                            stackNum.push(a1 - a2);
                            break;
                        case("*"):
                            if (stackNum.size() < 2) {
                                throw new IllegalExpressionException("Недостаточно аргументов для функции (*)");
                            }
                            a1 = stackNum.pop();
                            a2 = stackNum.pop();
                            stackNum.push(a1 * a2);
                            break;
                        case("/"):
                            if (stackNum.size() < 2) {
                                throw new IllegalExpressionException("Недостаточно аргументов для функции (/)");
                            }
                            a1 = stackNum.pop();
                            a2 = stackNum.pop();
                            if (a2.equals(0.0)) {
                                throw new DivisionByZeroException("Деление на 0 запрещено");
                            }
                            stackNum.push(a1 / a2);
                            break;
                        case("log"):
                            if (stackNum.size() < 1) {
                                throw new IllegalExpressionException("Недостаточно аргументов для функции log");
                            }
                            a1 = stackNum.pop();
                            if(a1 <= 0) {
                                throw new LogarithmIncorrectArgumentException("Аргумент логарифма должен быть больше 0");
                            }
                            stackNum.push(Math.log10(a1));
                            break;
                        case("pow"):
                            if (stackNum.size() < 2) {
                                throw new IllegalExpressionException("Недостаточно аргументов для функции pow");
                            }
                            a1 = stackNum.pop();
                            a2 = stackNum.pop();
                            stackNum.push(Math.pow(a1, a2));
                            break;
                        case("sqrt"):
                            if (stackNum.size() < 1) {
                                throw new IllegalExpressionException("Недостаточно аргументов для функции sqrt");
                            }
                            a1 = stackNum.pop();
                            if (a1 < 0) {
                                throw new RootLessThanZeroException("Корень не может быть меньше 0");
                            }
                            stackNum.push(Math.sqrt(a1));
                            break;
                        case("sin"):
                            if (stackNum.size() < 1) {
                                throw new IllegalExpressionException("Недостаточно аргументов для функции sin");
                            }
                            a1 = stackNum.pop();
                            stackNum.push(Math.sin(a1));
                            break;
                        case("cos"):
                            if (stackNum.size() < 1) {
                                throw new IllegalExpressionException("Недостаточно аргументов для функции cos");
                            }
                            a1 = stackNum.pop();
                            stackNum.push(Math.cos(a1));
                            break;
                    }
                }
                else {
                    if (expressionArr[i].equals("")) {
                        continue;
                    }
                    throw new IllegalExpressionException("Неизвестный оператор (" + expressionArr[i] + ")");
                }
            }
        }
        if (stackNum.size() == 1) {
            return stackNum.pop();
        }
        else {
            throw new IllegalExpressionException("Чисел больше чем нужно");
        }
    }
}
