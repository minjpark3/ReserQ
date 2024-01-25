package com.sparta.reserq.service;

import com.sparta.reserq.domain.user.User;
import com.sparta.reserq.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;
//    @Transactional
//    public User signUp (User user){
//        String rawPassword = user.getPassword();
//        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//        user.setPassword(encPassword);
//        User userEntity = userRepository.save(user);
//        return userEntity;
//    }
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

}

