package com.example.mo_product.stock.domain;

import com.example.mo_product.core.BaseCreatedUpdated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "productStock")
public class ProductStock {

    @Id
    @Column(name = "productId")
    private Long productId;

    @Column(name = "stockCount")
    private Integer stockCount;

    @CreationTimestamp
    @Column(name = "createDate")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(name = "updateDate")
    private LocalDateTime updateDate;

    @Builder
    public ProductStock(Long productId, Integer stockCount) {
        this.productId = productId;
        this.stockCount = stockCount;
    }

    public void updateStock(Integer quantity) {
        if(quantity != null) this.stockCount = this.stockCount - quantity;
    }


}