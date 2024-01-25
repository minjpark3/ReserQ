package com.sparta.reserq.config.auth;

import com.sparta.reserq.domain.user.User;
import com.sparta.reserq.dto.SignupDto;
import com.sparta.reserq.ex.CustomValidationException;
import com.sparta.reserq.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

//    @GetMapping("/auth/signin")
//    public String signinForm(){
//        return "auth/signin";
//    }

    @GetMapping("/auth/signup")
    public String signupForm(){
        return "auth/signup";
    }

//    @PostMapping("/auth/signup")
//    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            Map<String, String> errorMap = new HashMap<>();
//            for (FieldError error: bindingResult.getFieldErrors()){
//                errorMap.put(error.getField(), error.getDefaultMessage());
//                System.out.println(error.getDefaultMessage());
//            }
//            throw new CustomValidationException("유효성 검사 실패함", errorMap);
//        } else {
//            User user = signupDto.toEntity();
//            User userEntity = authService.signUp(user);
//            System.out.println(userEntity);
//            return "auth/signin";
//        }
//    }
@PostMapping("/auth/signup")
public ResponseEntity<String> signup(@RequestBody SignupDto signupDto) {
    // 유효성 검사를 수행하지 않고 진행

    // 나머지 코드는 동일하게 유지
    User user = signupDto.toEntity();
    User userEntity = authService.signUp(user);
    System.out.println(userEntity);
    return ResponseEntity.ok("ok");
}


}
