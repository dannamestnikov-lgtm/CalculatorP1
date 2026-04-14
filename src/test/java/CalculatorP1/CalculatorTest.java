package CalculatorP1;

import CalculatorP1.Service.Calculator;
import CalculatorP1.Util.History;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testAddition () {
    Calculator calculator = new Calculator();
    assertEquals(8.0, calculator.plus(5,3), 0.01);
    }

    @Test
    void testDivision(){
        Calculator calculator = new Calculator();
        assertEquals(2.5, calculator.division(5, 2), 0.01);
    }

    @Test
    void testDivisionByZero(){
        Calculator calculator = new Calculator();
        assertThrows(ArithmeticException.class , () -> calculator.division(5, 0));
    }

    @Test
    void testHistoryStoreLastTen(){
        History history = new History();
        for (int i = 0; i <= 11; i++) {
            history.add("op" + i);
        }
        assertEquals(10, history.size());
    }

    @Test
    void testClearHistory(){
        History history = new History();
        history.add("op 1");
        history.add("op 2");
        history.clear();
        assertEquals(0, history.size());
    }
}
