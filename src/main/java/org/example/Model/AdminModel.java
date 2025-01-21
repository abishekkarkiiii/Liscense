package org.example.Model;

import org.example.Entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public Connection connectionGiver() {
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


    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        try {
            // Execute the SQL query
            ResultSet resultSet = connectionGiver()
                    .createStatement()
                    .executeQuery("SELECT * FROM User");

            // Iterate through the result set and map data to User objects
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("UserID"));
                user.setUsername(resultSet.getString("UserName"));
                user.setPassword(resultSet.getString("Password"));
                user.setEmail(resultSet.getString("Email"));
                user.setAge(resultSet.getInt("Age"));
                user.setCitizenshipNumber(resultSet.getString("CitizenshipNumber"));
                user.setStatus(resultSet.getString("Status"));
                user.setCreateDate(resultSet.getTimestamp("CreatedAt").toString());
                // Add the User object to the li
                users.add(user);

                //user update ko liscense garna baki xa
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return users;
    }

    public void Approve(int id,boolean check){
        try(PreparedStatement statement=connectionGiver().prepareStatement("update User set Status=? where UserId=?")){
            statement.setString(1, check ? "passed" : "failed");
            statement.setInt(2,id);
            System.out.println(statement.executeUpdate()!=0?"Approved":"DisApproved");
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}
