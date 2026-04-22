package CalculatorP1.Util;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputParser {
    private final Scanner scanner = new Scanner(System.in);
    public String readInput() {
        System.out.println("Введите выражение:  " +
                "\n(или history - показать последние 10 операций " +
                "\nlast - показать последнюю операцию " +
                "\nclear - очистить историю операций " +
                "\nexit - выход из программы) ");
        return scanner.nextLine();
    }

    public String[] split(String input) {
        String operator = findOperator(input);
        String[] numbers = input.split(Pattern.quote(operator));
        validateParts(numbers);
        String leftPart = numbers[0].trim();
        String rightPart = numbers[1].trim();
        if (leftPart.isEmpty() || rightPart.isEmpty()) {
            throw new IllegalArgumentException("неверный формат");
        }
        return new String[]{leftPart, operator, rightPart};
    }

        public String findOperator(String input){
            String operator;
        if (input.contains("+")) {
            operator = "+";
        } else if (input.contains("-")) {
            operator = "-";
        } else if (input.contains("*")) {
            operator = "*";
        } else if (input.contains("/")) {
            operator = "/";
        } else {
            throw new IllegalArgumentException("оператор не найден");
        }
        return operator;
        }

        public void validateParts(String[] numbers) {
            if (numbers.length != 2) {
                throw new IllegalArgumentException("неверный формат");
            }
        }

    public double parseNumber(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
