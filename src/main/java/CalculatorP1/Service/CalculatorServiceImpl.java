package CalculatorP1.Service;

import CalculatorP1.Util.History;
import CalculatorP1.Util.InputParser;
import CalculatorP1.Util.WordUtil;

public class CalculatorServiceImpl implements CalculatorService {

    Calculator calculator = new Calculator();
    InputParser parser = new InputParser();
    History history = new History();
    WordUtil wordUtil = new WordUtil();

    // Ioc and DI - изучить

    @Override
    public void start() {
        while (true) {
            try {
                String input = parser.readInput();
                input = input.toLowerCase().trim();

                if (input.equals("exit")) {
                    System.out.println("Калькулятор закрыт");
                    break;
                }

                if (input.equals("history")) {
                    history.printHistory();
                    continue;
                }

                if (input.equals("last")) {
                    String last = history.getlast();
                    System.out.println(last == null ? "История пуста" : last);
                    continue;
                }

                if (input.equals("clear")) {
                    history.clear();
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
                    num2 = parser.parseNumber(parts[2].trim());
                }
                String operator = parts[1];

                double result;
                switch (operator) {
                    case "+":
                        result = calculator.plus(num1, num2);
                        break;
                    case "-":
                        result = calculator.minus(num1, num2);
                        break;
                    case "*":
                        result = calculator.multiplication(num1, num2);
                        break;
                    case "/":
                        result = calculator.division(num1, num2);
                        break;
                    default:
                        System.out.println("неизвестная операция");
                        continue;
                }
                if (isWord) {
                    if (result == (int) result) {
                        String resultWorld = wordUtil.numberToWord((int) result);
                        System.out.println(resultWorld);
                    } else {
                        System.out.println("результат не целое число " + result);;
                    }
                } else {
                    System.out.println(num1 + " " + operator + " " + num2 + " = " + result);
                }

                String expression = num1 + " " + operator + " " + num2 + " = " + result;
                history.add(expression);
            } catch (ArithmeticException e) {
                System.out.println("Ошибка. неверный формат");
            } catch (IllegalArgumentException e) {
                System.out.println("ошибка: деление на ноль");
            }
        }
    }
}