package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.ResetPasswordDTO;
import com.bridgelabz.employee_payroll.dto.UserDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.model.User;
import java.util.Optional;

public interface IUserService {

    ResponseDTO registerUser(UserDTO registerDTO);


    ResponseDTO loginUser(UserDTO loginDTO);


    Optional<User> getUserByEmail(String email);

    ResponseDTO forgotPassword(ResetPasswordDTO forgotPasswordDTO);

    ResponseDTO resetPassword(ResetPasswordDTO resetPasswordDTO);

}