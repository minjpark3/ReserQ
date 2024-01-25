package com.sparta.reserq.ex;

public class CustomException extends RuntimeException{

    //객체를 구분할때;
    private static final long serialVersionUID = 1L;

    public CustomException(String massage){
        super(massage);
    }
}