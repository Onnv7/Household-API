package com.onnv.household.controller;

import com.onnv.household.constants.SuccessConstant;
import com.onnv.household.dto.request.CreateCategoryRequest;
import com.onnv.household.dto.request.CreateProductRequest;
import com.onnv.household.dto.request.UpdateCategoryByIdRequest;
import com.onnv.household.dto.response.GetCategoryVisibleResponse;
import com.onnv.household.model.ResponseAPI;
import com.onnv.household.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.onnv.household.constants.EndpointConstant.*;
import static com.onnv.household.constants.SwaggerConstant.*;

@Tag(name = CATEGORY_TAG)
@RestController
@RequestMapping(CATEGORY_BASE_PATH)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = CATEGORY_CREATE_SUM)
    @PostMapping(path = POST_CATEGORY_CREATE_SUB_PATH)
    public ResponseEntity<ResponseAPI> createNewProduct(@RequestBody @Valid CreateCategoryRequest body) {
        categoryService.createCategory(body);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.CREATE_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = CATEGORY_GET_VISIBLE_SUM)
    @GetMapping(path = GET_CATEGORY_VISIBLE_SUB_PATH)
    public ResponseEntity<ResponseAPI> getCategoryVisible() {
        List<GetCategoryVisibleResponse> categoryList = categoryService.getCategoryVisible();
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.GET_SUCCESS)
                .data(categoryList)
                .build();
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = CATEGORY_UPDATE_BY_ID_SUM)
    @PutMapping(path = PUT_CATEGORY_UPDATE_BY_ID_SUB_PATH)
    public ResponseEntity<ResponseAPI> updateCategoryById(@PathVariable(CATEGORY_ID) String categoryId, @RequestBody @Valid UpdateCategoryByIdRequest body) {
         categoryService.updateCategoryById(categoryId, body);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.UPDATE_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = CATEGORY_DELETE_BY_ID_SUM)
    @DeleteMapping(path = DELETE_CATEGORY_BY_ID_SUB_PATH)
    public ResponseEntity<ResponseAPI> deleteCategoryById(@PathVariable(CATEGORY_ID) String categoryId) {
        categoryService.deleteCategoryById(categoryId);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.DELETE_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
