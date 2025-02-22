package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testAdd() {
        Calculator calc = new Calculator();
        int result = calc.add(3, 7);
        assertEquals(10, result, "3 + 7 = 10 이어야 합니다.");
    }

    @Test
    void testSubtract() {
        Calculator calc = new Calculator();
        int result = calc.subtract(10, 4);
        assertEquals(6, result, "10 - 4 = 6 이어야 합니다.");
    }
}