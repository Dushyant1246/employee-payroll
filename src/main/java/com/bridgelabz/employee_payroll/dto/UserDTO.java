package com.bridgelabz.employee_payroll.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String fullName;
    private String email;
    private String password;
    private String token;
    private String message;
}
