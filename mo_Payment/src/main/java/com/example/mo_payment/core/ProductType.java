package com.example.mo_payment.core;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductType {

    NORMAL("NORMAL"),
    RESERVED("RESERVED"),;

    private final String productType;

}
