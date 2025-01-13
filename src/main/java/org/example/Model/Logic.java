package org.example.Model;

import org.example.Entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.Scanner;

public class Logic {

    private final AdminModel adminModel = new AdminModel();

    public boolean isAdmin(String username, String password) {
        String query = "SELECT * FROM Admin WHERE Email = ? AND Password = ?";
        try (PreparedStatement statement = adminModel.connectionGiver().prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String adminName = resultSet.getString("UserName");
                System.out.printf("Welcome, %s! Do you want to create another admin? Press 1 to create or 2 to see all users.\n", adminName);

                Scanner input = new Scanner(System.in);
                int choice = input.nextInt();

                if (choice == 1) {
                    createNewAdmin(input);
                } else if (choice == 2) {
                    showAllUsers(input);
                }
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error during admin authentication: " + e.getMessage());
        }
        return false;
    }

    private void createNewAdmin(Scanner input) {
        System.out.println("Enter new admin details:");
        System.out.print("Name: ");
        String adminName = input.next();
        System.out.print("Email: ");
        String email = input.next();
        System.out.print("Password: ");
        String password = input.next();

        if (adminModel.createAdmin(adminName, password, email)) {
            System.out.println("Admin created successfully!");
        } else {
            System.out.println("Failed to create admin. Please try again.");
        }
    }

    private void showAllUsers(Scanner input) {
        adminModel.getAllUser().forEach(user -> {
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getUsername());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Age: " + user.getAge());
            System.out.println("Citizenship Number: " + user.getCitizenshipNumber());
            System.out.println("Status: " + user.getStatus());
            System.out.println("Created At: " + user.getCreateDate());
            System.out.println("---------------------------------");
        });

        System.out.println("Enter 1 to approve/disapprove a user or any other key to exit.");
        if (input.nextInt() == 1) {
            System.out.print("Enter User ID to approve: ");
            int userId = input.nextInt();
            System.out.println("Press Y to Approved Else Press N to Fail");
            boolean check=false;
            if(input.next().toLowerCase(Locale.ROOT).equals("y")){
               check=true;
                System.out.println("User approved successfully.");
            }else {
                adminModel.Approve(userId,check);
                System.out.println("User Failed Updated");

            }

        }
    }

    public boolean createAccount(User user) {
        String query = "INSERT INTO User (UserName, Password, Email, Age, CitizenshipNumber) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = adminModel.connectionGiver().prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getAge());
            statement.setString(5, user.getCitizenshipNumber());

            if (statement.executeUpdate() > 0) {
                System.out.println("Account created successfully!");
                return true;
            } else {
                System.out.println("Failed to create account. Please try again.");
            }
        } catch (Exception e) {
            System.err.println("Error during account creation: " + e.getMessage());
        }
        return false;
    }
}
