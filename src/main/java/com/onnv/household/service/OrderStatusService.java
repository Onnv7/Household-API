package com.onnv.household.service;

import com.onnv.household.entity.OrderStatusEntity;
import com.onnv.household.repository.OrderStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusEntity createOrderStatus(OrderStatusEntity data) {
        return orderStatusRepository.save(data);
    }
}
