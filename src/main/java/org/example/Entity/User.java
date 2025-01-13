package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {
    private String username;
    private String citizenshipNumber;
    private String Status;
    private int age;
    private String email;
    private String password;
    private int Id;
    private String createDate;
}
