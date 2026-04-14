package CalculatorP1.Service;

public class Calculator {
    public double plus(double a, double b) {
        return a + b;
    }

    public double minus(double a, double b) {
        return a - b;
    }

    public double multiplication(double a, double b) {
        return a * b;
    }

    public double division(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Ошибка. Деление на ноль");
        }
        return a / b;
    }
}
