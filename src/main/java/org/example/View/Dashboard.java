package org.example.View;

import java.util.Scanner;

import org.example.Entity.User;
import org.example.Model.Logic;
import org.example.Model.UserModel;

public class Dashboard {

    private final Logic logic = new Logic();
    private final UserModel userModel = new UserModel();

    public Dashboard() {
        initializeDashboard();
    }

    private void initializeDashboard() {
        Scanner scanner = new Scanner(System.in);

        printHeader();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Login");
            System.out.println("2. Apply for License");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
           try{

           
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
        
            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    createAccount(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using the Advanced License Tracker. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid input! Please try again.");
            }
        }catch(Exception e){
            System.out.println("Please enter once again");

        }
        }
    }

    private void printHeader() {
        System.out.println("\n=========================================");
        System.out.println("     Advanced License Tracker System     ");
        System.out.println("=========================================");
    }

    private void login(Scanner scanner) {
        System.out.println("\n--- Login ---");
        System.out.print("Enter your Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your Password: ");
        String password = scanner.nextLine();

        if (logic.isAdmin(email, password)) {
            System.out.println("Admin login successful.");
        } else {
            User user = userModel.userLogin(email, password);
            if (user != null) {
                System.out.printf("Welcome, %s! Here is your information:\n", user.getUsername());
                System.out.println("ID: " + user.getId());
                System.out.println("Citizenship Number: " + user.getCitizenshipNumber());
                System.out.println("Age: " + user.getAge());
                System.out.println("License Status: " + user.getStatus());
            } else {
                System.out.println("Invalid email or password. Please try again.");
            }
        }
    }

    private void createAccount(Scanner scanner) {
        System.out.println("\n--- Apply for License ---");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Citizenship Number: ");
        String citizenshipNumber = scanner.nextLine();

        if (age >= 18) {
            User user = new User(username, citizenshipNumber, "pending", age, email, password, 0, "");
            if (logic.createAccount(user)) {
                System.out.println("Account created successfully! Please log in to continue.");
            }
        } else {
            System.out.println("You must be at least 18 years old to apply for a license.");
        }
    }
}
