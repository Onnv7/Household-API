package com.onnv.household.service;

import com.onnv.household.constants.CloudinaryConstant;
import com.onnv.household.constants.ErrorConstant;
import com.onnv.household.dto.request.CreateProductRequest;
import com.onnv.household.dto.request.UpdateProductByIdRequest;
import com.onnv.household.dto.response.GetProductDetailsNonHiddenByIdResponse;
import com.onnv.household.dto.response.GetProductNonHiddenByCategoryIdResponse;
import com.onnv.household.dto.response.GetProductNonHiddenResponse;
import com.onnv.household.entity.CategoryEntity;
import com.onnv.household.entity.ProductClassificationEntity;
import com.onnv.household.entity.ProductEntity;
import com.onnv.household.entity.ProductImageEntity;
import com.onnv.household.enums.ProductStatus;
import com.onnv.household.model.CustomException;
import com.onnv.household.repository.ProductRepository;
import com.onnv.household.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final RandomUtils randomUtils;
    private final ModelMapperUtils modelMapperUtils;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    public final ProductImageService productImageService;
    private final ImageUtils imageUtils;
    private final CloudinaryUtils cloudinaryUtils;

    public ProductEntity getProductById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ErrorConstant.NOT_FOUND + " - " + productId));
    }
    @Transactional
    public void createProduct(CreateProductRequest data) throws IOException {
        CategoryEntity category = categoryService.findCategoryById(data.getCategoryId());
        if(category.getChildCategoryList() != null && category.getChildCategoryList().size() > 0) {
            throw new CustomException(ErrorConstant.CATEGORY_INVALID);
        }
        ProductEntity product = modelMapperUtils.mapClass(data, ProductEntity.class);
        List<ProductClassificationEntity> productClassificationList = product.getProductClassificationList();
        if((productClassificationList == null || productClassificationList.isEmpty()) && product.getPrice() == null) {
            throw new CustomException(ErrorConstant.PRICE_AND_CLASSIFICATION_IS_NULL);
        } else if(productClassificationList != null && productClassificationList.size() > 0) {
            Double minPrice = productClassificationList.stream().mapToDouble(ProductClassificationEntity::getPrice)
                    .min().orElseThrow(() -> new CustomException(ErrorConstant.MIN_PRICE_INVALID));
            product.setPrice(minPrice);

            productClassificationList.stream()
                    .forEach(it -> it.setProduct(product));
        }
        product.setCategory(category);

        List<MultipartFile> imageList = data.getImageList();
        List<Map<String, String>> imageCloudinary = new ArrayList<>();

        byte[] thumbnailImageBytes = imageUtils.resizeImage(data.getThumbnailImage().getBytes(), 200, 200);
        HashMap<String, String> thumbUploaded = cloudinaryUtils.uploadFileToFolder(
                CloudinaryConstant.PRODUCT_PATH,
                TextUtils.replaceNonAlphanumeric(data.getName()) + "_thumb_" + RandomUtils.randomTimeId() ,
                thumbnailImageBytes
        );

        ProductImageEntity thumbnail = ProductImageEntity.builder()
                .id(thumbUploaded.get(CloudinaryConstant.PUBLIC_ID))
                .url(thumbUploaded.get(CloudinaryConstant.URL_PROPERTY))
                .product(product)
                .isThumbnail(true)
                .build();

        imageList.stream()
                .forEach(image -> {
                    try {
                        byte[] originalImage = image.getBytes();
                        byte[] newImage = imageUtils.resizeImage(originalImage, 200, 200);

                        HashMap<String, String> imageUploaded = cloudinaryUtils.uploadFileToFolder(
                                CloudinaryConstant.PRODUCT_PATH,
                                TextUtils.replaceNonAlphanumeric(data.getName()) + "_" + RandomUtils.randomTimeId(),
                                newImage
                        );
                        imageCloudinary.add(imageUploaded);
                        product.getProductImageList().add(ProductImageEntity.builder()
                                .id(imageUploaded.get(CloudinaryConstant.PUBLIC_ID))
                                .url(imageUploaded.get(CloudinaryConstant.URL_PROPERTY))
                                .product(product)
                                .build());
                    } catch (IOException e) {
                        throw new CustomException(ErrorConstant.IMAGE_LOADING_ERROR);
                    } catch (Exception e) {
                        imageCloudinary.stream().forEach(it->{
                            try {
                                cloudinaryUtils.deleteImage(it.get(CloudinaryConstant.PUBLIC_ID));
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                        throw new CustomException(ErrorConstant.IMAGE_LOADING_ERROR);
                    }
                    finally {
                        imageCloudinary.clear();
                    }
                });
        product.getProductImageList().add(thumbnail);
        productRepository.save(product);
    }

    public List<GetProductNonHiddenResponse> getProductNonHidden(int page, int size) {
        int offset = (page - 1) * size;
        List<GetProductNonHiddenResponse> productList = productRepository.getProductNonHidden(offset, size);

        return productList;
    }
    public List<String> getChildCategoryIdList(CategoryEntity category) {
        List<String> categoryList = new ArrayList<String>();
        if((category.getProductList() != null && category.getProductList().size() > 0) || (category.getChildCategoryList() == null || category.getChildCategoryList().isEmpty()) ) {
            categoryList.add(category.getId());
        } else {
            category.getChildCategoryList().stream()
                    .forEach(it -> {
                        categoryList.addAll(getChildCategoryIdList(it));
                    });

        }
        return categoryList;
    }
    public List<GetProductNonHiddenByCategoryIdResponse> getProductNonHiddenByCategoryId(String categoryId) {
        CategoryEntity category = categoryService.findCategoryById(categoryId);
        List<String> categoryIdList = new ArrayList<String>();
        if(category.getProductList() == null || category.getProductList().isEmpty()) {
            categoryIdList = getChildCategoryIdList(category);
        } else {
            categoryIdList.add(category.getId());
        }
        List<GetProductNonHiddenByCategoryIdResponse> productList = productRepository.getProductListByCategoryIn(categoryIdList);
//        List<ProductEntity> productList = productRepository.findByCategoryIn(category.getChildCategoryList());
        return productList;
    }

    public GetProductDetailsNonHiddenByIdResponse getProductNonHiddenById(String productId) {
        ProductEntity product = productRepository.findByIdAndStatusNot(productId, ProductStatus.HIDDEN);
        GetProductDetailsNonHiddenByIdResponse productData = modelMapperUtils.mapNotNullClass(product, GetProductDetailsNonHiddenByIdResponse.class);
        return productData;
    }

    @Transactional
    public void deleteProductById(String productId) {
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new CustomException(ErrorConstant.NOT_FOUND + " - " + productId));
        List<ProductImageEntity> productImageList = product.getProductImageList();
        productImageList.stream()
                        .forEach(it -> {
                            try {
                                cloudinaryUtils.deleteImage(it.getId());
                            } catch (IOException e) {
                                throw new CustomException(ErrorConstant.DELETE_IMAGE_FAILED);
                            }
                        });
        productRepository.deleteById(productId);
    }

    @Transactional
    public void updateProductById(UpdateProductByIdRequest data, String productId) {
        ProductEntity product = getProductById(productId);
        modelMapperUtils.map(data, product);
        productRepository.save(product);
    }


}
