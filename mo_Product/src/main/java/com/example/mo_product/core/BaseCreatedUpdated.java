package com.example.mo_product.core;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseCreatedUpdated {

    @CreatedDate
    @Column(name = "createDate")
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "updateDate")
    private LocalDateTime updateDate;
}
