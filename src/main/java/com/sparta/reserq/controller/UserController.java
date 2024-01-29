package com.sparta.reserq.controller;

import com.sparta.reserq.core.annotaion.MyErrorLog;
import com.sparta.reserq.core.annotaion.MyLog;
import com.sparta.reserq.core.auth.jwt.MyJwtProvider;
import com.sparta.reserq.core.auth.session.MyUserDetails;
import com.sparta.reserq.domain.user.User;
import com.sparta.reserq.dto.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.sparta.reserq.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    @MyErrorLog
    @MyLog
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserRequest.JoinInDTO joinInDTO, Errors errors) throws Exception {
        UserResponse.JoinOutDTO joinOutDTO = userService.signup(joinInDTO);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(joinOutDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestBody VerificationRequest verificationRequest) {
        String email = verificationRequest.getEmail();
        String token = verificationRequest.getToken();

        boolean verificationResult = userService.verifyVerificationToken(email, token);

        if (verificationResult) {
            return ResponseEntity.ok("가입 완료.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email verification failed.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginInDTO loginInDTO) throws Exception {
        String jwt = userService.login(loginInDTO);
        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        return ResponseEntity.ok().header(MyJwtProvider.HEADER, jwt).body(responseDTO);
    }

//@PostMapping("/login")
//public ResponseEntity<?> login(@RequestBody UserRequest.LoginInDTO loginInDTO) {
//    try {
//        String jwt = userService.userlogin(loginInDTO);
//        ResponseDTO<?> responseDTO = new ResponseDTO<>();
//        return ResponseEntity.ok().header(MyJwtProvider.HEADER, jwt).body(responseDTO);
//    } catch (AuthenticationException e) {
//        // Spring Security에서 제공하는 AuthenticationException을 잡아서 처리
//        // 예를 들어, BadCredentialsException, DisabledException 등이 여기에 해당
//        String errorMessage = "Authentication failed: " + e.getMessage();
//        ResponseDTO<String> errorResponse = new ResponseDTO<>(errorMessage);
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
//    } catch (Exception e) {
//        // 그 외의 예외가 발생한 경우
//        String errorMessage = "Authentication failed: " + e.getMessage();
//        ResponseDTO<String> errorResponse = new ResponseDTO<>(errorMessage);
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//    }
//}
//
//    @MyLog
//    public String userlogin(UserRequest.LoginInDTO loginInDTO) throws Exception {
//        try {
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
//                    = new UsernamePasswordAuthenticationToken(loginInDTO.getEmail(), loginInDTO.getPassword());
//            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
//            return MyJwtProvider.create(myUserDetails.getUser());
//        } catch (Exception e) {
//            // Print the exception message to console
//            e.printStackTrace();
//            throw new Exception("Authentication failed: " + e.getMessage());
//        }
//    }


    @GetMapping("/user/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id, @AuthenticationPrincipal MyUserDetails myUserDetails) throws Exception {
        if(id.longValue() != myUserDetails.getUser().getId()){
            throw new Exception("권한이 없습니다");
        }
        UserResponse.DetailOutDTO detailOutDTO = userService.회원상세보기(id);
        //System.out.println(new ObjectMapper().writeValueAsString(detailOutDTO));
        ResponseDTO<?> responseDTO = new ResponseDTO<>(detailOutDTO);
        return ResponseEntity.ok(responseDTO);
    }
}