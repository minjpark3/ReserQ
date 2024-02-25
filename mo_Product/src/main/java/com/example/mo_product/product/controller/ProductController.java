package com.example.mo_product.product.controller;

import com.example.mo_product.product.domain.Product;
import com.example.mo_product.product.dto.ProductCreateReq;
import com.example.mo_product.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // 새로운 상품을 생성하는 엔드포인트
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductCreateReq productCreateReq) {
        String itemName = productService.create(productCreateReq.getUserId(), productCreateReq);
        return ResponseEntity.ok(itemName + " 상품이 생성되었습니다.");
    }

    // 모든 상품 목록을 조회하는 엔드포인트
    @GetMapping
    public ResponseEntity<List<Product>> getProductList() {
        List<Product> productList = productService.getProductList();
        return ResponseEntity.ok(productList);
    }

    // 특정 상품을 조회하는 엔드포인트
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        Product product = productService.getProduct(productId);
        return ResponseEntity.ok(product);
    }
}