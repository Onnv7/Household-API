package com.onnv.household.controller;

import com.onnv.household.constants.SuccessConstant;
import com.onnv.household.dto.request.CreateProductRequest;
import com.onnv.household.dto.request.UpdateProductByIdRequest;
import com.onnv.household.dto.response.GetProductNonHiddenResponse;
import com.onnv.household.model.ResponseAPI;
import com.onnv.household.service.ProductClassificationService;
import com.onnv.household.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.onnv.household.constants.EndpointConstant.*;
import static com.onnv.household.constants.SwaggerConstant.*;

@Tag(name = PRODUCT_TAG)
@RestController
@RequestMapping(PRODUCT_BASE_PATH)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductClassificationService productClassificationService;

    @Operation(summary = PRODUCT_CREATE_SUM)
    @PostMapping(path = POST_PRODUCT_CREATE_SUB_PATH, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseAPI> createNewProduct(@ModelAttribute @Valid CreateProductRequest body) throws IOException {
        productService.createProduct(body);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.CREATE_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = PRODUCT_GET_NOT_HIDDEN_SUM)
    @GetMapping(path = GET_PRODUCT_NOT_HIDDEN_SUB_PATH)
    public ResponseEntity<ResponseAPI> getProductNonHidden(
            @Parameter(name = PAGE_KEY, required = true, example = "1")
            @RequestParam(name = PAGE_KEY)   @Min(value = 1, message = "Page must be greater than 0") int page,
            @Parameter(name = SIZE_KEY, required = true, example = "10")
            @RequestParam(name = SIZE_KEY) @Min(value = 1, message = "Size must be greater than 0")  int size
    ) {
        List<GetProductNonHiddenResponse> resData = productService.getProductNonHidden(page, size);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.GET_SUCCESS)
                .data(resData)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = PRODUCT_GET_NOT_HIDDEN_BY_CATEGORY_SUM)
    @GetMapping(path = GET_PRODUCT_NOT_HIDDEN_BY_CATEGORY_SUB_PATH)
    public ResponseEntity<ResponseAPI> getProductNonHiddenByCategoryId(@PathVariable(CATEGORY_ID) String categoryId) {
        var resData = productService.getProductNonHiddenByCategoryId(categoryId);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.GET_SUCCESS)
                .data(resData)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = PRODUCT_GET_NOT_HIDDEN_BY_ID_SUM)
    @GetMapping(path = GET_PRODUCT_NOT_HIDDEN_BY_ID_SUB_PATH)
    public ResponseEntity<ResponseAPI> getProductNonHiddenByById(@PathVariable(PRODUCT_ID) String productId) {
        var resData = productService.getProductNonHiddenById(productId);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.GET_SUCCESS)
                .data(resData)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @Operation(summary = PRODUCT_DELETE_BY_ID_SUM)
    @DeleteMapping(path = DELETE_PRODUCT_BY_ID_SUB_PATH)
    public ResponseEntity<ResponseAPI> deleteProductById(@PathVariable(PRODUCT_ID) String productId) {
        productService.deleteProductById(productId);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.DELETE_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = PRODUCT_UPDATE_BY_ID_SUM)
    @PutMapping(path = PUT_PRODUCT_UPDATE_BY_ID_SUB_PATH)
    public ResponseEntity<ResponseAPI> updateProductById(@PathVariable(PRODUCT_ID) String productId, @ModelAttribute @Valid UpdateProductByIdRequest body) {
        productService.updateProductById(body, productId);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.UPDATE_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
