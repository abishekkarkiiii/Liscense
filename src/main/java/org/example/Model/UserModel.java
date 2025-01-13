package org.example.Model;



import org.example.Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserModel {
    AdminModel adminModel=new AdminModel();
   public User userLogin(String email, String password){
        Connection conn=adminModel.connectionGiver();
        try(PreparedStatement preparedStatement=conn.prepareStatement("select * from User where Email=? and Password=?")){
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                //MethodChaning Gareko taki direct user buld hos;
                return User.builder().username(resultSet.getString("UserName"))
                        .age(resultSet.findColumn("Age")).Status(statusCheck(resultSet.getString("Status")))
                        .email(resultSet.getString("Email")).Id(resultSet.getInt("UserId")).citizenshipNumber(resultSet.getString("CitizenshipNumber`````````")).build();
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }


    private String statusCheck(String status){
        return status.equals("pending") ? "pending" :
                status.equals("passed") ? "Congratulation you have passed a License" :
                        "Sorry, you can't pass the license examination";
    }


}
