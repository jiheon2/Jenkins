package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        // System.out을 캡처하기 위해 리다이렉트
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        // 테스트 후 원래의 System.out으로 복원
        System.setOut(originalOut);
    }

    @Test
    void testGetGreetingOutputAndReturn() {
        Main main = new Main();
        String result = main.getGreeting();

        // 반환값 검증
        assertEquals("finish", result, "getGreeting() 메소드는 \"finish\"를 반환해야 합니다.");

        String output = outputStreamCaptor.toString();

        // "Hello, Jenkins!"가 10번 출력되었는지 확인
        assertEquals(10, countOccurrences(output, "Hello, Jenkins!"), "Hello, Jenkins!가 10번 출력되어야 합니다.");

        // 카운트다운 숫자들이 10번씩 출력되었는지 확인
        assertEquals(10, countOccurrences(output, "5"), "5가 10번 출력되어야 합니다.");
        assertEquals(10, countOccurrences(output, "4"), "4가 10번 출력되어야 합니다.");
        assertEquals(10, countOccurrences(output, "3"), "3이 10번 출력되어야 합니다.");
        assertEquals(10, countOccurrences(output, "2"), "2가 10번 출력되어야 합니다.");
        assertEquals(10, countOccurrences(output, "1"), "1이 10번 출력되어야 합니다.");

        // "프로그램을 종료합니다."가 한 번 출력되었는지 확인
        assertEquals(1, countOccurrences(output, "프로그램을 종료합니다."), "프로그램 종료 메시지가 한 번 출력되어야 합니다.");
    }

    @Test
    void testMainMethodOutput() {
        // outputStream 초기화
        outputStreamCaptor.reset();
        Main.main(new String[]{});

        String output = outputStreamCaptor.toString();

        // "Hello, Jenkins!"가 10번 출력되었는지 확인
        assertEquals(10, countOccurrences(output, "Hello, Jenkins!"), "Hello, Jenkins!가 10번 출력되어야 합니다.");

        // 카운트다운 숫자들이 10번씩 출력되었는지 확인
        assertEquals(10, countOccurrences(output, "5"), "5가 10번 출력되어야 합니다.");
        assertEquals(10, countOccurrences(output, "4"), "4가 10번 출력되어야 합니다.");
        assertEquals(10, countOccurrences(output, "3"), "3이 10번 출력되어야 합니다.");
        assertEquals(10, countOccurrences(output, "2"), "2가 10번 출력되어야 합니다.");
        assertEquals(10, countOccurrences(output, "1"), "1이 10번 출력되어야 합니다.");

        // "프로그램을 종료합니다."가 한 번 출력되었는지 확인
        assertEquals(1, countOccurrences(output, "프로그램을 종료합니다."), "프로그램 종료 메시지가 한 번 출력되어야 합니다.");
    }

    // 문자열 내 특정 부분 문자열의 발생 횟수를 세는 헬퍼 메소드
    private int countOccurrences(String str, String subStr) {
        int count = 0;
        int index = 0;
        while ((index = str.indexOf(subStr, index)) != -1) {
            count++;
            index += subStr.length();
        }
        return count;
    }
}
