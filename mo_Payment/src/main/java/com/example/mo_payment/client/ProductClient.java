package com.example.mo_payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Configuration
@FeignClient(name = "productClient",  url = "http://localhost:8090")
public interface ProductClient {

    @GetMapping("/api/internal/products/{productId}/getPrice")
    Integer getProductPrice(@PathVariable("productId") Long productId);

    @GetMapping("/api/internal/productStocks/{productId}")
    Integer getProductStock(@PathVariable("productId") Long productId);

    @GetMapping("/api/internal/reservedProductStocks/{reservedProductId}")
    Integer getReservedProductStock(@PathVariable("reservedProductId") String reservedProductId);

    @PostMapping("/api/internal/productStocks/{productId}/decreasedStock")
    void decreasedProductStock(@PathVariable("productId") Long productId,
                               @RequestParam(name = "quantity") Integer quantity);

    @PostMapping("/api/internal/reservedProductStocks/{reservedProductId}/decreasedStock")
    void decreasedReservedProductStock(@PathVariable("reservedProductId") String reservedProductId,
                                       @RequestParam(name = "quantity") String quantity);

}