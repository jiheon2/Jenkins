package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testGetGreeting() {
        Main main = new Main();
        String greeting = main.getGreeting();
        assertEquals("Hello, Jenkins!", greeting, "테스트 성공 조건은 'Hello, Jenkins!' 출력");
    }
}