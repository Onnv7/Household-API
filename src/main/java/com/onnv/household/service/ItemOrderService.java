package com.onnv.household.service;

import com.onnv.household.entity.ItemOrderEntity;
import com.onnv.household.repository.ItemOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemOrderService {
    private final ItemOrderRepository itemOrderRepository;

    public ItemOrderEntity createItemOrder(ItemOrderEntity data) {
        return itemOrderRepository.save(data);
    }
}
