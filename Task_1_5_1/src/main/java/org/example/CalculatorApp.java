package org.example;

import org.example.exceptions.DivisionByZeroException;
import org.example.exceptions.IllegalExpressionException;
import org.example.exceptions.LogarithmIncorrectArgumentException;
import org.example.exceptions.RootLessThanZeroException;

import java.util.Scanner;

import static org.example.Calculator.calculateExpression;

public class CalculatorApp {
    public static void calculatorApp() {
        while(true) {
            System.out.print("Введите выражение в префиксной форме. Чтобы выйти введите exit: ");
            Scanner s = new Scanner(System.in);
            String expression = s.nextLine();
            if (expression.equals("exit")) {
                return ;
            }
            try {
                Double result = calculateExpression(expression);
                System.out.print("Result: ");
                System.out.println(result);
            } catch (LogarithmIncorrectArgumentException | DivisionByZeroException | IllegalExpressionException |
                     RootLessThanZeroException e) {
                System.out.print("ERROR: ");
                System.out.println(e.getMessage());
            }
        }
    }
}
