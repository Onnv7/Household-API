package com.onnv.household.service;

import com.onnv.household.constants.ErrorConstant;
import com.onnv.household.entity.ProductEntity;
import com.onnv.household.model.CustomException;
import com.onnv.household.repository.ProductClassificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductClassificationService {
    private final ProductClassificationRepository productClassificationRepository;

    public Double getPriceOfProductType(String productId, String typeName) {
        Double price =  productClassificationRepository.getProductPriceByTypeName(productId, typeName);
        if(price == null) {
            throw new CustomException(ErrorConstant.NOT_FOUND);
        }
        return price;
    }
}
