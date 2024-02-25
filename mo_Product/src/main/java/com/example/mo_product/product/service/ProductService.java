package com.example.mo_product.product.service;

import com.example.mo_product.product.domain.Product;
import com.example.mo_product.product.domain.ProductRepository;
import com.example.mo_product.product.dto.ProductCreateReq;
import com.example.mo_product.stock.domain.ProductStock;
import com.example.mo_product.stock.domain.ProductStockRepository;
import com.example.mo_product.stock.service.ProductStockService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Builder
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductStockRepository productStockRepository;
    private final ProductStockService productStockService;


    //상품 등록
    @Transactional
    public String create(Long userId, ProductCreateReq productCreateReq) {
        Product product = Product.builder()
                .userId(userId)
                .itemName(productCreateReq.getItemName())
                .content(productCreateReq.getContent())
                .price(productCreateReq.getPrice())
                .build();
        Product savedProduct = productRepository.save(product);

        // ProductStock 엔티티 생성 및 저장
        ProductStock stock = ProductStock.builder()
                .productId(savedProduct.getId())
                .stockCount(productCreateReq.getStock())
                .build();
        productStockRepository.save(stock);

        return savedProduct.getItemName();
    }

    // 일반 상품 모든목록 조회
    @Transactional(readOnly = true)
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    // 일반 상품 상세 조회
    @Transactional(readOnly = true)
    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("상품이 존재하지 않습니다."));
    }


}