package com.sparta.reserq.ex;

public class CustomApiException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    public CustomApiException(String massage) {
        super(massage);

    }

}