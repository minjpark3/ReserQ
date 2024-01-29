package com.sparta.reserq.service;

import com.sparta.reserq.core.annotaion.MyLog;
import com.sparta.reserq.core.auth.jwt.MyJwtProvider;
import com.sparta.reserq.core.auth.session.MyUserDetails;
import com.sparta.reserq.domain.user.User;
import com.sparta.reserq.domain.user.UserRepository;
import com.sparta.reserq.dto.TokenDTO;
import com.sparta.reserq.dto.UserRequest;
import com.sparta.reserq.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    @MyLog
    @Transactional
    public UserResponse.JoinOutDTO signup(UserRequest.JoinInDTO joinInDTO) throws Exception {
        Optional<User> userOP = userRepository.findByEmail(joinInDTO.getEmail());
        if (userOP.isPresent()) {
            throw new Exception("이미 등록된 이메일입니다.");
        }

        String encPassword = bCryptPasswordEncoder.encode(joinInDTO.getPassword());
        joinInDTO.setPassword(encPassword);

        try {
            User userPS = userRepository.save(joinInDTO.toEntity());
            signUpAndSendEmail(userPS);
            return new UserResponse.JoinOutDTO(userPS);
        } catch (Exception e) {
            throw new Exception("회원가입 실패 : " + e.getMessage());
        }
    }

    @MyLog
    public boolean verifyVerificationToken(String email, String verificationToken) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getVerificationToken().equals(verificationToken)) {
                user.setVerified(true);
                userRepository.save(user);
                return true;
            } else {
                userRepository.delete(user);
            }
        }
        return false;
    }

    @MyLog
    @Transactional
    public String login(UserRequest.LoginInDTO loginInDTO) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(loginInDTO.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!user.isVerified()) {
                throw new Exception("이메일 인증이 완료되지 않았습니다.");
            }

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(loginInDTO.getEmail(), loginInDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
            return MyJwtProvider.create(myUserDetails.getUser());
        } else {
            throw new Exception("해당 이메일로 가입된 계정이 없습니다.");
        }
    }

    private void signUpAndSendEmail(User user) {
        String verificationToken = generateVerificationToken();
        user.setVerificationToken(verificationToken);
        userRepository.save(user);
        emailService.sendVerificationEmail(user.getEmail(), verificationToken);
    }

    private String generateVerificationToken() {
        int randomNumber = (int) ((Math.random() * (999999 - 100000 + 1)) + 100000);
        return String.valueOf(randomNumber);
    }
    @MyLog
    public UserResponse.DetailOutDTO 회원상세보기(Long id) throws Exception {
        User userPS = userRepository.findById(id)
                .orElseThrow(() -> new Exception("해당 유저를 찾을 수 없습니다"));

        return new UserResponse.DetailOutDTO(userPS);
    }


}
