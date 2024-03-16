package com.example.mo_product.product.dto;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class ProductCreateReq {
        private Long userId;
        private String itemName;
        private String content;
        private int price;
        private int stock = 0;

}
