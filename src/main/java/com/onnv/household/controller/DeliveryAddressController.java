package com.onnv.household.controller;

import com.onnv.household.constants.SuccessConstant;
import com.onnv.household.dto.request.CreateDeliveryAddressRequest;
import com.onnv.household.dto.request.UpdateDeliveryAddressByIdRequest;
import com.onnv.household.dto.response.GetDeliveryAddressByUserIdResponse;
import com.onnv.household.model.ResponseAPI;
import com.onnv.household.service.DeliveryAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.onnv.household.constants.EndpointConstant.*;
import static com.onnv.household.constants.SwaggerConstant.*;

@Tag(name = DELIVERY_ADDRESS_TAG)
@RestController
@RequestMapping(DELIVERY_ADDRESS_BASE_PATH)
@RequiredArgsConstructor
public class DeliveryAddressController {
    private final DeliveryAddressService deliveryAddressService;

    @Operation(summary = ADDRESS_CREATE_SUM)
    @PostMapping(path = POST_ADDRESS_CREATE_SUB_PATH)
    public ResponseEntity<ResponseAPI> createNewDeliveryAddress(@RequestBody @Valid CreateDeliveryAddressRequest body, @PathVariable(USER_ID) String userId) {
        deliveryAddressService.createDeliveryAddress(body, userId);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.CREATE_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = ADDRESS_GET_BY_USER_ID_SUM)
    @GetMapping(path = GET_ADDRESS_BY_USER_ID_SUB_PATH)
    public ResponseEntity<ResponseAPI> getAddressByUserId(@PathVariable(USER_ID) String userId) {
        List<GetDeliveryAddressByUserIdResponse> resData = deliveryAddressService.getAddressByUserId(userId);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.GET_SUCCESS)
                .data(resData)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = ADDRESS_UPDATE_BY_USER_ID_SUM)
    @PutMapping(path = PUT_ADDRESS_UPDATE_BY_ID_SUB_PATH)
    public ResponseEntity<ResponseAPI> updateDeliveryAddressById(@PathVariable(ADDRESS_ID) String addressId, @RequestBody @Valid UpdateDeliveryAddressByIdRequest body) {
        deliveryAddressService.updateDeliveryAddressById(addressId, body);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.UPDATE_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @Operation(summary = ADDRESS_DELETE_BY_USER_ID_SUM)
    @DeleteMapping(path = DELETE_ADDRESS_BY_ID_SUB_PATH)
    public ResponseEntity<ResponseAPI> deleteDeliveryAddressById(@PathVariable(ADDRESS_ID) String addressId) {
        deliveryAddressService.deleteDeliveryAddressById(addressId);
        ResponseAPI res = ResponseAPI.builder()
                .message(SuccessConstant.DELETE_SUCCESS)
                .build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
