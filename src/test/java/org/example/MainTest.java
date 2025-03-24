package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.*;

public class MainTest {

    @Test// 전체 테스트가 5초 이내에 종료되어야 합니다.
    public void testGetGreetingInfiniteLoopWithFuture() throws InterruptedException {
        Main main = new Main();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> main.getGreeting());

        try {
            // 3초 동안 결과가 없으면 TimeoutException 발생, 이는 무한 루프가 동작 중임을 의미합니다.
            future.get( 1, TimeUnit.SECONDS);
            fail("getGreeting 메서드는 무한 루프를 실행해야 하므로, 결과가 반환되면 안됩니다.");
        } catch (TimeoutException e) {
            // 예상한 상황: 무한 루프이므로 타임아웃이 발생함.
        } catch (ExecutionException e) {
            fail("예상치 못한 예외 발생: " + e.getCause());
        } finally {
            // 테스트 종료 전에 작업을 취소하고, ExecutorService를 종료합니다.
            future.cancel(true);
            executor.shutdownNow();
        }
    }
}
