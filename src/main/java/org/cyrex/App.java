package org.cyrex;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        while (true) {
            System.out.print("calculate: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String result = Calculator.calc(input);
            System.out.println("result: " + result);
        }
    }
}