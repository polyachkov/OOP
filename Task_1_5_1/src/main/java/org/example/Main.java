package org.example;

import org.example.exceptions.DivisionByZeroException;
import org.example.exceptions.IllegalExpressionException;
import org.example.exceptions.LogarithmIncorrectArgumentException;
import org.example.exceptions.RootLessThanZeroException;

import java.util.Scanner;

import static org.example.Calculator.calculateExpression;

public class Main {
    public static void main(String[] args) throws IllegalExpressionException, DivisionByZeroException, LogarithmIncorrectArgumentException, RootLessThanZeroException {
        CalculatorApp.calculatorApp();
        System.out.println("END");
    }
}