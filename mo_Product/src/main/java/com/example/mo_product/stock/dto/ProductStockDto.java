package com.example.mo_product.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ProductStockDto {

    private Long productId;
    private int stockCount;

}
