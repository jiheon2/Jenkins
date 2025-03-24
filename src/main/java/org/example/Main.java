package org.example;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello, Jenkins!");
            System.out.println("5");
            System.out.println("4");
            System.out.println("3");
            System.out.println("2");
            System.out.println("1");
            if (i == 9) {
                System.out.println("프로그램을 종료합니다.");
            }
        }
    }

    public String getGreeting() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello, Jenkins!");
            System.out.println("5");
            System.out.println("4");
            System.out.println("3");
            System.out.println("2");
            System.out.println("1");
            if (i == 9) {
                System.out.println("프로그램을 종료합니다.");
            }
        }
        return "finish";
    }
}