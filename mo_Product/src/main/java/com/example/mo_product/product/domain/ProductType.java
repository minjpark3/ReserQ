package com.example.mo_product.product.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductType {

    NORMAL("normal"),
    RESERVED("reserved"),;

    private final String productType;

}
