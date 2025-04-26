package com.bridgelabz.employee_payroll.controller;

import com.bridgelabz.employee_payroll.dto.ResetPasswordDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.dto.UserDTO;
import com.bridgelabz.employee_payroll.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") // Matches "/auth/register" and "/auth/login" from security config
@CrossOrigin(origins = "*")
class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO registerDTO) {
        ResponseDTO response = userService.registerUser(registerDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody UserDTO loginDTO) {
        ResponseDTO response = userService.loginUser(loginDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseDTO> forgotPassword(@RequestBody ResetPasswordDTO forgotPasswordDTO){
        ResponseDTO response = userService.forgotPassword(forgotPasswordDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResponseDTO> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO){
        ResponseDTO response = userService.resetPassword(resetPasswordDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}