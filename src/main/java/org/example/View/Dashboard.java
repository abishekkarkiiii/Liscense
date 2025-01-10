package org.example.View;

import java.util.Scanner;

public class Dashboard {

    public Dashboard(){
        initializeDashboard();
    }
    public void initializeDashboard() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("=========================================");
        System.out.println("   Welcome to Advanced License Tracker   ");
        System.out.println("=========================================");
        System.out.println("1. Login");
        System.out.println("2. Create Account");
        System.out.println("3. Exit");
        System.out.println("-----------------------------------------");

        while (true) {
            System.out.print("Please enter your choice (1-3): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Redirecting to Login...");
                    addDelay(2000); // 2-second delay
                    // Call your login method here
                    break;
                case 2:
                    System.out.println("Redirecting to Account Creation...");
                    addDelay(2000); // 2-second delay

                    break;
                case 3:
                    System.out.println("Thank you for using the system. Goodbye!");
                    addDelay(1500); // 1.5-second delay before exit
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    // Method to introduce a delay using Thread.sleep
    private void addDelay(int milliseconds) {
        try {
            Thread.sleep(milliseconds); // Pause execution for the specified time
        } catch (InterruptedException e) {
            System.out.println("Unexpected interruption occurred.");
        }
    }
}
