package com.onnv.household.service;

import com.onnv.household.entity.ShipmentDetailsEntity;
import com.onnv.household.repository.ShipmentDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipmentDetailsService {
    private final ShipmentDetailsRepository shipmentDetailsRepository;

    public ShipmentDetailsEntity createShipmentDetails(ShipmentDetailsEntity data) {
        return shipmentDetailsRepository.save(data);
    }
}
