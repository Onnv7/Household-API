package com.onnv.household.service;

import com.onnv.household.constants.ErrorConstant;
import com.onnv.household.dto.request.CreateDeliveryAddressRequest;
import com.onnv.household.dto.request.UpdateDeliveryAddressByIdRequest;
import com.onnv.household.dto.response.GetDeliveryAddressByUserIdResponse;
import com.onnv.household.entity.DeliveryAddressEntity;
import com.onnv.household.entity.UserEntity;
import com.onnv.household.model.CustomException;
import com.onnv.household.repository.DeliveryAddressRepository;
import com.onnv.household.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryAddressService {
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final UserService userService;
    private final ModelMapperUtils modelMapperUtils;

    public DeliveryAddressEntity getDeliveryAddressById(String addressId) {
        return deliveryAddressRepository.findById(addressId).orElseThrow(() -> new CustomException(ErrorConstant.NOT_FOUND));
    }
    public void createDeliveryAddress(CreateDeliveryAddressRequest data, String userId) {
        UserEntity user = userService.getUserById(userId);
        DeliveryAddressEntity address = modelMapperUtils.mapClass(data, DeliveryAddressEntity.class);
        address.setUser(user);
        if (user.getDeliveryAddressList().size() == 0) {
            address.setDefault(true);
        } else if  (user.getDeliveryAddressList().size() == 3) {
            throw new CustomException(ErrorConstant.OVER_3_ADDRESS);
        }
        deliveryAddressRepository.save(address);
    }

    public List<GetDeliveryAddressByUserIdResponse> getAddressByUserId(String userId) {
        List<GetDeliveryAddressByUserIdResponse> addressList = deliveryAddressRepository.getDeliveryAddressByUserId(userId);
        return addressList;
    }

    public void updateDeliveryAddressById(String addressId, UpdateDeliveryAddressByIdRequest data) {
        DeliveryAddressEntity address = getDeliveryAddressById(addressId);
        modelMapperUtils.mapNotNull(data, address);
        deliveryAddressRepository.save(address);
    }

    @Transactional
    public void deleteDeliveryAddressById(String addressId) {
        DeliveryAddressEntity address =  getDeliveryAddressById(addressId);
        deliveryAddressRepository.delete(address);
    }
}
