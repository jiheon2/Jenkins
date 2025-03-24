package org.example;

public class Main {
    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Hello, Jenkins!");
                System.out.println("5");
                System.out.println("4");
                System.out.println("3");
                System.out.println("2");
                System.out.println("1");
                if (i == 9) {
                    System.out.println("프로그램을 종료하지 않고 계속 실행합니다.");
                }
            }
            // 루프 간 잠시 대기 (예: 1초)하여 CPU 사용률을 낮춥니다.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public String getGreeting() {
        while (true) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Hello, Jenkins!");
                System.out.println("5");
                System.out.println("4");
                System.out.println("3");
                System.out.println("2");
                System.out.println("1");
                if (i == 9) {
                    System.out.println("프로그램을 종료하지 않고 계속 실행합니다.");
                }
            }
            // 루프 간 잠시 대기 (예: 1초)하여 CPU 사용률을 낮춥니다.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}