package com.sparta.reserq.domain.user;

import com.sparta.reserq.config.auth.PrincipalDetails;
import com.sparta.reserq.dto.CMRespDto;
import com.sparta.reserq.dto.FollowerDto;
import com.sparta.reserq.dto.UserProfileDto;
import com.sparta.reserq.dto.UserUpdateDto;
import com.sparta.reserq.ex.CustomValidationApiException;
import com.sparta.reserq.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserContoller {
    private final UserService userService;

//    @GetMapping("/user/{pageUserId}")
//    public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
//        UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
//        model.addAttribute("dto", dto);
//        return "user/profile";
//    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails){

        return "user/update";
    }
    @PutMapping("/api/user/{principalId}/profileImageUrl")
    public ResponseEntity<?> profileImageUrlUpdate(@PathVariable int principalId, MultipartFile profileImageFile, @AuthenticationPrincipal PrincipalDetails principalDetails){// jsp파일에서 name 인 profileImageFile 와 동일하게 해줘야 매칭되서 받아줌
        User userEntity = userService.회원프로필사진변경(principalId,profileImageFile);
        principalDetails.setUser(userEntity);//세션변경
        return new ResponseEntity<>(new CMRespDto<>(1,"프로필 사진변경 성공",null), HttpStatus.OK);
    }

//    @GetMapping("/api/user/{pageUserId}/subscribe")
//    public ResponseEntity<?>subscribeList(@PathVariable int pageUserId,@AuthenticationPrincipal PrincipalDetails principalDetails){
//        List<FollowerDto> followerDtos = followerService.구독리스트(principalDetails.getUser().getId(),pageUserId);
//        return new ResponseEntity<>(new CMRespDto<>(1,"구독자 리스트 불러오기 성공",subscribeDto), HttpStatus.OK);
//    }

//    @PutMapping("/api/user/{id}")
//    public CMRespDto<?> update(@PathVariable int id,
//                               @Valid UserUpdateDto userUpdateDto,
//                               BindingResult bindingResult, //꼭 valid가 적은다음 파라메타에 적어야됨
//                               @AuthenticationPrincipal PrincipalDetails principalDetails){
//        if(bindingResult.hasErrors()){
//            Map<String, String> errorMap = new HashMap<>();
//            for (FieldError error: bindingResult.getFieldErrors()){
//                errorMap.put(error.getField(),error.getDefaultMessage());
//            }
//            throw new CustomValidationApiException("유효성 검사 실패함",errorMap);
//        }else {
//            User userEntity = userService.회원수정(id,userUpdateDto.toEntity());
//            principalDetails.setUser(userEntity);
//            return new CMRespDto<>(1,"회원수정완료",userEntity); //응답시에 userEntity의 모든 getter함수가 호출되고 JSON으로 파싱해서 응답한다.
//        }
//    }
}