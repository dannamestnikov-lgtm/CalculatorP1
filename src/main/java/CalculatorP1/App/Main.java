package CalculatorP1.App;


import CalculatorP1.Service.CalculatorService;
import CalculatorP1.Service.CalculatorServiceImpl;

public class Main {
    public static void main(String[] args) {
        CalculatorService service = new CalculatorServiceImpl();
        service.start();


    }
}
