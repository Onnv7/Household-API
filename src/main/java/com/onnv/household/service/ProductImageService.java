package com.onnv.household.service;

import com.onnv.household.entity.ProductImageEntity;
import com.onnv.household.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductImageService {
    private final ProductImageRepository productImageRepository;

    public ProductImageEntity createProductImage(ProductImageEntity data) {
        return productImageRepository.save(data);
    }
}
