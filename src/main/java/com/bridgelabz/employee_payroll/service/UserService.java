package com.bridgelabz.employee_payroll.service;

import com.bridgelabz.employee_payroll.dto.ResetPasswordDTO;
import com.bridgelabz.employee_payroll.dto.ResponseDTO;
import com.bridgelabz.employee_payroll.dto.UserDTO;
import com.bridgelabz.employee_payroll.model.User;
import com.bridgelabz.employee_payroll.repository.UserRepository;
import com.bridgelabz.employee_payroll.utility.JwtUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Slf4j
@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public ResponseDTO registerUser(UserDTO registerDTO) {
        log.info("Registering user:{}",registerDTO.getEmail());
        ResponseDTO res = new ResponseDTO();
        if(existsByEmail(registerDTO.getEmail())){
            log.warn("registration failed: user already exists with email {}",registerDTO.getEmail());
            res.setMessage("user already exists");
            return res;
        }
        User user = new User();
        user.setFullName(registerDTO.getFullName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("user {} registered successfully",user.getEmail());
        res.setMessage("user registered successfully");
        return res;
    }

    @Override
    public ResponseDTO loginUser(UserDTO loginDTO) {
        log.info("Login attempt for user: {}", loginDTO.getEmail());
        ResponseDTO res = new ResponseDTO();
        Optional<User> userOptional = getUserByEmail(loginDTO.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (matchPassword(loginDTO.getPassword(), user.getPassword())) {
                String token = jwtUtility.generateToken(user.getEmail());
                user.setToken(token);
                userRepository.save(user);

                log.debug("Login successful for user: {} - Token generated", user.getEmail());
                res.setMessage("User Logged In Successfully: " + token);
            } else {
                log.warn("Invalid credentials for user: {}", loginDTO.getEmail());
                res.setMessage("Invalid Credentials");
            }
            return res;
        }

        log.error("User not found with email: {}", loginDTO.getEmail());
        res.setMessage("User Not Found");
        return res;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public ResponseDTO forgotPassword(ResetPasswordDTO forgotPasswordDTO) {
        Optional<User> userOptional = userRepository.findByEmail(forgotPasswordDTO.getEmail());
        if (userOptional.isEmpty()){
            return new ResponseDTO("error","User not found with this email");
        }
        User user = userOptional.get();
        String otp = String.valueOf(100000 + new SecureRandom().nextInt(900000));
        user.setOtp(otp);
        userRepository.save(user);

        emailService.sendEmail(forgotPasswordDTO.getEmail(), "Reset Password", "OTP to reset your password: " + otp);
        return new ResponseDTO(otp, "Reset OTP Sent");
    }

    @Override
    public ResponseDTO resetPassword(ResetPasswordDTO resetPasswordDTO) {
        Optional<User> userOptional = userRepository.findByEmail(resetPasswordDTO.getEmail());
        if ((userOptional.isEmpty())){
            return new ResponseDTO("error", "User not found with this email");
        }
        User user = userOptional.get();
        if (user.getOtp().equals(resetPasswordDTO.getOtp())){
            user.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
            user.setOtp(null);
            userRepository.save(user);
            emailService.sendEmail(resetPasswordDTO.getEmail(), "Password Updated", "Password has been changed and token has expired");
            return new ResponseDTO("Password has been changed and token has expired","Password Updated");
        }
        return new ResponseDTO("Password not changed check your OTP again","Invalid OTP");
    }


    public boolean matchPassword(String rawPassword, String encodedPassword) {
        log.debug("Matching password for login attempt");
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
