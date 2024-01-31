package com.sparta.reserq.controller;

import com.sparta.reserq.domain.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequiredArgsConstructor
public class commetController {
    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity<?> commnetSave(@Valid @RequestBody CommentDto commentDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails){
        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error: bindingResult.getFieldErrors()){
                errorMap.put(error.getField(),error.getDefaultMessage());
                // System.out.println(error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패함",errorMap);
        }
        Comment comment = commentService.댓글쓰기(commentDto.getContent(),commentDto.getImageId(),principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글쓰기 성공",comment), HttpStatus.CREATED);
    }
    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<?>commnetDelete(@PathVariable int id){
        commentService.댓글삭제(id);
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글삭제성공",null), HttpStatus.OK);
    }

}