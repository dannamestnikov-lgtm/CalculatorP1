package CalculatorP1.Util;

import java.util.HashMap;
import java.util.Map;

public class WordUtil {
    private Map<String, Integer> wordToNumber;
    private Map<Integer, String> numberToWord;

    public WordUtil() {
        wordToNumber = new HashMap<>();
        numberToWord = new HashMap<>();
        wordToNumber.put("один", 1);
        wordToNumber.put("два", 2);
        wordToNumber.put("три", 3);
        wordToNumber.put("четыре", 4);
        wordToNumber.put("пять", 5);
        wordToNumber.put("шесть", 6);
        wordToNumber.put("семь", 7);
        wordToNumber.put("восемь", 8);
        wordToNumber.put("девять", 9);
        wordToNumber.put("десять", 10);
        wordToNumber.put("одиннадцать", 11);
        wordToNumber.put("двенадцать", 12);
        wordToNumber.put("тринадцать", 13);
        wordToNumber.put("четырнадцать", 14);
        wordToNumber.put("пятнадцать", 15);
        wordToNumber.put("шестнадцать", 16);
        wordToNumber.put("семнадцать", 17);
        wordToNumber.put("восемнадцать", 18);
        wordToNumber.put("девятнадцать", 19);
        wordToNumber.put("двадцать", 20);
        wordToNumber.put("тридцать", 30);
        wordToNumber.put("сорок", 40);
        wordToNumber.put("пятьдесят", 50);
        wordToNumber.put("шестьдесят", 60);
        wordToNumber.put("семьдесят", 70);
        wordToNumber.put("восемьдесят", 80);
        wordToNumber.put("девяносто", 90);
        wordToNumber.put("сто", 100);
        for (Map.Entry<String, Integer> entry : wordToNumber.entrySet()){
            numberToWord.put(entry.getValue(), entry.getKey());
        }
    }

    public int wordToNumber(String input) {
        String [] words = input.trim().split(" ");
        int result = 0;

        for (String word : words) {
            Integer value = wordToNumber.get(word);
            if (value == null){
                throw new IllegalArgumentException("неверное слово: " + word);
            }
            result += value;
        }
        return result;
    }
    public String numberToWord(int number){
        if (numberToWord.containsKey(number)) {
            return numberToWord.get(number);
        }
        int tens = splitTens(number);
        int ones = number % 10;

        String tensWord = numberToWord.get(tens);
        String onesWord = numberToWord.get(ones);

        if (ones == 0) {
            return tensWord;
        }
        return tensWord + " " + onesWord;
    }

    private int splitTens(int number){
        return (number / 10) * 10;
    }

    public boolean isWordInput(String input){
        return input.matches(".*[а-яА-Я]+.*");
    }

    public String replaceOperators(String input) {
        input = input.replace("плюс", "+");
        input = input.replace("минус", "-");
        input = input.replace("умножить на", "*");
        input = input.replace("делить на", "/");
        return input;
    }
}

