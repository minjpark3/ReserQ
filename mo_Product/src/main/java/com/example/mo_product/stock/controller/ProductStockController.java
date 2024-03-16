package com.example.mo_product.stock.controller;

import com.example.mo_product.stock.dto.ProductStockDto;
import com.example.mo_product.stock.service.ProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/productStock")
public class ProductStockController {

    private final ProductStockService productStockService;

    @PostMapping("/create")
    public void createProductStock(@RequestBody ProductStockDto productStockDto) {
        productStockService.createProductStock(productStockDto);
    }

    @GetMapping("/{productId}")
    public Integer getProductStock(@PathVariable Long productId) {
        return productStockService.getProductStock(productId);
    }


    @PostMapping("/{productId}/decreasedStock")
    public ResponseEntity<?> decreasedProductStock(@PathVariable("productId") Long productId,
                                                   @RequestBody Map<String, Integer> requestBody) {
        Integer quantity = requestBody.get("quantity");
        if (quantity == null) {
            return ResponseEntity.badRequest().body("Quantity is missing in the request body.");
        }

        productStockService.decreasedProductStock(productId, quantity);
        return ResponseEntity.ok().build();
    }
//    @PostMapping("/decrease/{productId}")
//    public void decreaseProductStock(@PathVariable Long productId, @RequestParam Integer quantity) {
//        productStockService.decreasedProductStock(productId, quantity);
//    }

//    @PostMapping("/{productId}/decreasedStock")
//    public ResponseEntity<?> decreasedProductStock(@PathVariable("productId") String productId,
//                                                   @RequestParam(name = "quantity") String quantity) {
//        productStockService.decreasedProductStock(
//                Long.valueOf(productId), Integer.valueOf(quantity));
//
//        return ResponseEntity.ok().build();
//    }
//    @PostMapping("/{productId}/decreasedStock")
//    public ResponseEntity<?> decreasedProductStock(@PathVariable("productId") Long productId,
//                                                   @RequestParam(name = "quantity") Integer quantity) {
//        productStockService.decreasedProductStock(productId, quantity);
//
//        return ResponseEntity.ok().build();
//    }
}

