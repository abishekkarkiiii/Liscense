package org.example.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminModel {

    private String url = "jdbc:mysql://127.0.0.1:3306/LiscenseManagement";
    private String username = "root";
    private String password = "root";

    public boolean createAdmin(String userName, String password, String email) {
        Connection conn = connectionGiver();
        if (conn != null) {
            if (userName == null || password == null || email == null) {
                System.out.println("Please provide all required data.");
                return false;
            }

            String sql = "INSERT INTO Admin (UserName, Password, Email) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email);

                int rowsAffected = preparedStatement.executeUpdate(); // Execute the query

                if (rowsAffected > 0) {
                    System.out.println("Successfully created admin.");
                    return true;
                } else {
                    System.out.println("Failed to create admin.");
                    return false;
                }

            } catch (SQLException e) {
                System.out.println("Error while creating admin: " + e.getMessage());
                return false;
            }

        } else {
            System.out.println("Connection not established.");
            return false;
        }
    }

    private Connection connectionGiver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connection Successful");
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException exception) {
            System.out.println("Connection can't be established: " + exception.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver can't be loaded: " + e.getMessage());
            return null;
        }
    }
}
