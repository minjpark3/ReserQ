package com.sparta.reserq.ex;

import com.sparta.reserq.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(CustomValidationException.class)
    public String vaildationException(CustomValidationException e) {
        //CMRespDto, Script비교 -개발자가 응답받을땐 CMR~ 이편하고 클라이언트가 받을땐 스크립트가 편하다.
        //1.클라이언트에게 응답할때는 Script가 좋음
        //2.Ajax통신- CMRespDto
        //3.Android통신- CMRespDto

        if (e.getErrorMap() == null) {
            return Script.back(e.getMessage());

        } else {
            return Script.back(e.getErrorMap().toString());
        }
    }

    @ExceptionHandler(CustomException.class)
    public String vaildationException(CustomException e) {
        return Script.back(e.getMessage());
    }


    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> vaildationApiException(CustomValidationApiException e){
        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e){
        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null),HttpStatus.BAD_REQUEST);
    }
}