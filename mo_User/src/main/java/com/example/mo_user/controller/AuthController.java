package com.example.mo_user.controller;

import com.example.mo_user.domain.dto.LoginRequest;
import com.example.mo_user.domain.dto.SignupDto;
import com.example.mo_user.domain.dto.VerificationRequest;
import com.example.mo_user.domain.user.User;
import com.example.mo_user.service.UserService;
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
    private final UserService authService;

    //회원가입
    @PostMapping("/api/join")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupDto signupDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 유효성 검사 에러가 있을 경우 처리
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입실패.");
        } else {
            // 유효성 검사 통과 시 회원가입 진행
            User user = signupDto.toEntity();
            authService.signUpAndSendEmail(user);
            return ResponseEntity.ok("이메일에서 인증번호 확인 해보세요.");
        }
    }

    //가입시 메일인증
    @PostMapping("/api/verify")
    public ResponseEntity<String> verifyEmail(@RequestBody VerificationRequest verificationRequest) {
        String email = verificationRequest.getEmail();
        String token = verificationRequest.getToken();

        // authService를 사용하여 email과 token을 검증하는 로직 추가
        boolean verificationResult = authService.verifyVerificationToken(email, token);

        if (verificationResult) {
            // 인증 성공
            return ResponseEntity.ok("회원가입 성공.");
        } else {
            // 인증 실패
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이메일 인증실패, 다시 가입해주세요.");
        }
    }

    //로그인
    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            String jwtToken = authService.login(loginRequest);
            return ResponseEntity.ok(jwtToken);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패. " + e.getMessage());
        }
    }



}

