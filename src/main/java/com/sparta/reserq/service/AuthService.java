package com.sparta.reserq.service;
import com.sparta.reserq.domain.user.User;
import com.sparta.reserq.domain.user.UserRepository;
import com.sparta.reserq.dto.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
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


}

