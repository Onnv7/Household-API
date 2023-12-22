package com.onnv.household.service;

import com.onnv.household.constants.ErrorConstant;
import com.onnv.household.dto.request.CreateCategoryRequest;
import com.onnv.household.dto.request.UpdateCategoryByIdRequest;
import com.onnv.household.dto.response.GetCategoryVisibleResponse;
import com.onnv.household.entity.CategoryEntity;
import com.onnv.household.enums.CategoryStatus;
import com.onnv.household.model.CustomException;
import com.onnv.household.repository.CategoryRepository;
import com.onnv.household.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.onnv.household.constants.BeanConstant.MODEL_MAPPER_NOT_NULL;
import static com.onnv.household.constants.ErrorConstant.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapperUtils modelMapperUtils;

    public CategoryEntity findCategoryById(String categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() ->  new CustomException(CATEGORY_NOT_FOUND + " - " + categoryId));
    }
    public void createCategory(CreateCategoryRequest data) {
        CategoryEntity category = modelMapperUtils.mapNotNullClass(data, CategoryEntity.class);
        if(data.getParentCategoryId() != null) {
            CategoryEntity parentCategory = findCategoryById(data.getParentCategoryId());
            category.setParentCategory(parentCategory);
        }
        categoryRepository.save(category);
    }
    public List<GetCategoryVisibleResponse> getCategoryVisible() {
        List<CategoryEntity> categories = categoryRepository.findByStatusAndParentCategoryIsNull(CategoryStatus.VISIBLE);
        List<GetCategoryVisibleResponse> categoryList = modelMapperUtils.mapListNotNull(categories, GetCategoryVisibleResponse.class);
       return categoryList;
    }

    public void updateCategoryById(String categoryId, UpdateCategoryByIdRequest data) {
        CategoryEntity category = findCategoryById(categoryId);
        if(data.getParentCategoryId() == null) {
            category.setParentCategory(null);
        } else {
            CategoryEntity parentCategory = findCategoryById(data.getParentCategoryId());
            category.setParentCategory(parentCategory);
        }
        modelMapperUtils.map(data, category);
        categoryRepository.save(category);
    }

    public void deleteCategoryById(String categoryId) {
        if(categoryRepository.existsById(categoryId))
            categoryRepository.deleteById(categoryId);
        else {
            throw new CustomException(ErrorConstant.NOT_FOUND + " - " + categoryId);
        }
    }
}
