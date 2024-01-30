package com.sparta.reserq.service;

import com.sparta.reserq.domain.dto.LoginRequest;
import com.sparta.reserq.domain.user.User;
import com.sparta.reserq.domain.user.UserRepository;
import com.sparta.reserq.jwt.JwtTokenProvider;
import com.sparta.reserq.jwt.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void signUpAndSendEmail(User user) {
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);

        String verificationToken = generateVerificationToken();
        user.setVerificationToken(verificationToken);

        userRepository.save(user);
        emailService.sendVerificationEmail(user.getEmail(), verificationToken);
    }
    private String generateVerificationToken() {
        int randomNumber = (int) ((Math.random() * (999999 - 100000 + 1)) + 100000);

        return String.valueOf(randomNumber);
    }
    @Transactional
    public boolean verifyVerificationToken(String email, String verificationToken) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getVerificationToken().equals(verificationToken)) {
                user.setVerified(true);
                userRepository.save(user);
                return true;
            } else {
                // 인증 실패 시 디비에서 해당 유저 삭제
                userRepository.delete(user);
                // 추가해야될게 시간초과시 디비 삭제부분도 만들기 이메일 1번에 1개밖에 못받아서 가입불가
            }
        }
        return false;
    }


    public String login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                // Passwords match, generate and return JWT token using JwtTokenProvider
                return jwtTokenProvider.generate(email, user.getName(), TokenType.ACCESS);
            } else {
                throw new IllegalArgumentException("Invalid password");
            }
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
