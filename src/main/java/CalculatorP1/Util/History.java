package CalculatorP1.Util;

import java.util.ArrayList;
import java.util.List;

public class History {
    private final List<String> history = new ArrayList<>();

    public void add(String operation){
        history.add(operation);
        if (history.size() > 10){
            history.remove(0);
        }
    }

    public void printHistory(){
        if (history.isEmpty()) {
            System.out.println("История пуста");
            return;
        }
        System.out.println("последние 10 операций:");
        for (String record : history) {
            System.out.println(record);
        }
    }

    public String getlast() {
        if (history.isEmpty()) {
            return null;
        }
        return history.get(history.size() - 1);
    }
    public void clear(){
        history.clear();
        System.out.println("История очищена");
    }
    public int size() {
        return history.size();
    }
}