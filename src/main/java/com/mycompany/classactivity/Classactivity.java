/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.classactivity;

import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class Classactivity {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Main Menu
            System.out.println("\n=== SMART UTILITY MENU ===");
            System.out.println("1. Student Performance Analyzer");
            System.out.println("2. Number Property Checker");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    studentPerformanceAnalyzer(scanner);
                    break;
                case 2:
                    numberPropertyChecker(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using the Smart Utility Menu. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // Option 1: Student Performance Analyzer
    public static void studentPerformanceAnalyzer(Scanner scanner) {
        System.out.println("\n--- Student Performance Analyzer ---");

        int[] scores = new int[3];
        boolean criticalFail = false;
        int total = 0;

        for (int i = 0; i < 3; i++) {
            System.out.print("Enter exam score " + (i + 1) + " (0-100): ");
            scores[i] = scanner.nextInt();

            if (scores[i] < 0 || scores[i] > 100) {
                System.out.println("Invalid score. Please enter a value between 0 and 100.");
                return;
            }

            if (scores[i] < 40) {
                criticalFail = true;
            }

            total += scores[i];
        }

        if (criticalFail) {
            System.out.println("Performance Level: Fail (Critical)");
            return;
        }

        double average = total / 3.0;

        if (average >= 90) {
            System.out.println("Performance Level: Excellent");
        } else if (average >= 80) {
            System.out.println("Performance Level: Very Good");
        } else if (average >= 70) {
            System.out.println("Performance Level: Good");
        } else if (average >= 60) {
            System.out.println("Performance Level: Satisfactory");
        } else {
            System.out.println("Performance Level: Needs Improvement");
        }
    }

    // Option 2: Number Property Checker
    public static void numberPropertyChecker(Scanner scanner) {
        System.out.println("\n--- Number Property Checker ---");

        System.out.print("Enter a positive integer: ");
        int number = scanner.nextInt();

        if (number <= 0) {
            System.out.println("Please enter a positive integer.");
            return;
        }

        String result = "";

        switch (number % 2) {
            case 0: // Even
                switch (number % 4) {
                    case 0:
                        result = "Even & Divisible by 4";
                        break;
                    default:
                        result = "Even & Not divisible by 4";
                        break;
                }
                break;

            case 1: // Odd
                if (isPrime(number)) {
                    result = "Odd & Prime";
                } else {
                    result = "Odd & Not Prime";
                }
                break;
        }

        System.out.println("Result: " + result);
    }

    // Helper method to check if a number is prime
    public static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}