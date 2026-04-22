package CalculatorP1.Service;

import CalculatorP1.Util.History;
import CalculatorP1.Util.InputParser;
import CalculatorP1.Util.WordUtil;

public class CalculatorServiceImpl implements CalculatorService {

    Calculator calculator = new Calculator();
    InputParser parser = new InputParser();
    History history = new History();
    WordUtil wordUtil = new WordUtil();

    @Override
    public void start() {
        while (true) {
            try {
                String input = parser.readInput();
                input = input.trim().toLowerCase();
                if (input.equals("exit")){
                    System.out.println("калькулятор закрыт");
                    break;
                }
                if (processCommand(input)){
                    continue;
                }

                input = wordUtil.replaceOperators(input);
                boolean isWord = wordUtil.isWordInput(input);
                String [] parts = parser.split(input);
                double num1;
                double num2;
                if (isWord) {
                    num1 = wordUtil.wordToNumber(parts[0].trim());
                    num2 = wordUtil.wordToNumber(parts[2].trim());
                } else {
                    num1 = parser.parseNumber(parts[0].trim());
                    num2 = parser.parseNumber(parts[2].trim()); }

                String operator = parts[1];
                double result = 0;
                result = calculateResult(num1, num2, operator);
                printResult(result, num1, num2, operator, isWord);
                saveHistory(num1, operator, num2, result);

            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: неверный формат");
            } catch (ArithmeticException e) {
                System.out.println("ошибка: деление на ноль"); }
        }
    }

    public boolean processCommand(String input){
        if (input.equals("history")) {
            history.printHistory();
            return true;
        }
        if (input.equals("last")) {
            String last = history.getlast();
            System.out.println(last == null ? "История пуста" : last);
            return true;
        }
        if (input.equals("clear")) {
            history.clear();
            return true;
        }
        return false;
    }

    public double calculateResult(double num1, double num2, String operator){
        switch (operator) {
            case "+":
                double result = calculator.plus(num1, num2);
                return result;
            case "-":
                result = calculator.minus(num1, num2);
                return result;
            case "*":
                result = calculator.multiplication(num1, num2);
                return result;
            case "/":
                result = calculator.division(num1, num2);
                return result;
            default:
        }
        return 0;
    }

    public void printResult(double result, double num1, double num2,
                               String operator, boolean isWord){
        if (isWord) {
            if (result == (int) result) {
                String resultWord = wordUtil.numberToWord((int) result);
                System.out.println(resultWord);
            } else {
                System.out.println("результат не целое число " + result);; }
        } else {
            System.out.println(num1 + " " + operator + " " + num2 + " = " + result);
        }
    }

    public void saveHistory(double num1, String operator, double num2, double result){
        String expression = num1 + " " + operator + " " + num2 + " = " + result;
        history.add(expression);
    }
}