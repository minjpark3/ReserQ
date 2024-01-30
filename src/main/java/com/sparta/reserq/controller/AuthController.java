package com.sparta.reserq.controller;

import com.sparta.reserq.domain.dto.LoginRequest;
import com.sparta.reserq.domain.dto.SignupDto;
import com.sparta.reserq.domain.dto.VerificationRequest;
import com.sparta.reserq.domain.user.User;
import com.sparta.reserq.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;


    @PostMapping("/api/join")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupDto signupDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 유효성 검사 에러가 있을 경우 처리
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Signup failed due to validation errors.");
        } else {
            // 유효성 검사 통과 시 회원가입 진행
            User user = signupDto.toEntity();
            authService.signUpAndSendEmail(user);
            return ResponseEntity.ok("Signup successful. Please check your email for verification.");
        }
    }

    @PostMapping("/api/verify")
    public ResponseEntity<String> verifyEmail(@RequestBody VerificationRequest verificationRequest) {
        String email = verificationRequest.getEmail();
        String token = verificationRequest.getToken();

        // authService를 사용하여 email과 token을 검증하는 로직 추가
        boolean verificationResult = authService.verifyVerificationToken(email, token);

        if (verificationResult) {
            // 인증 성공
            return ResponseEntity.ok("Email verification successful.");
        } else {
            // 인증 실패
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email verification failed.");
        }
    }
    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            String jwtToken = authService.login(loginRequest);
            return ResponseEntity.ok(jwtToken);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed. " + e.getMessage());
        }
    }



}

