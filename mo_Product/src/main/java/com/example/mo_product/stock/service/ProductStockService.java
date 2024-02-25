package com.example.mo_product.stock.service;

import com.example.mo_product.stock.domain.ProductStock;
import com.example.mo_product.stock.domain.ProductStockRepository;
import com.example.mo_product.stock.dto.ProductStockDto;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Builder
@RequiredArgsConstructor
@Service
public class ProductStockService {

    private final ProductStockRepository productStockRepository;

    //수량생성
    public void createProductStock(ProductStockDto productStockDto){
        ProductStock productStock = ProductStock.builder()
                .productId(productStockDto.getProductId())
                .stockCount(productStockDto.getStockCount())
                .build();
        productStockRepository.save(productStock);
    }

    //재고 확인
    @Transactional(readOnly = true)
    public Integer getProductStock(Long productId) {
        ProductStock productStock =
                productStockRepository.findByProductId(productId)
                        .orElseThrow(() -> new RuntimeException("해당 상품의 재고가 존재하지 않습니다."));

        return productStock.getStockCount();
    }
    //재고 감소
    @Transactional
    public void decreasedProductStock(Long productId, Integer quantity) {
        ProductStock productStock =
                productStockRepository.findByProductId(productId)
                        .orElseThrow(() -> new RuntimeException("해당 상품의 재고가 존재하지 않습니다."));

        if (productStock.getStockCount() <= 0) {
            throw new RuntimeException("해당 상품의 재고가 존재하지 않습니다.");
        }

        if (productStock.getStockCount() < quantity) {
            throw new RuntimeException("해당 상품의 재고가 부족합니다.");
        }

        productStock.updateStock(quantity);
    }

}