package com.onnv.household.repository;

import com.onnv.household.dto.response.GetDeliveryAddressByUserIdResponse;
import com.onnv.household.entity.DeliveryAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddressEntity, String> {
    @Query(value = """
            select da.id, concat_ws(' - ',da.ward, da.district, da.province) as supAddress, da.details as subAddress, da.phone_number as phoneNumber, da.is_default as isDefault
            from delivery_address da
            join `user` u on da.user_id = u.id
            where u.id = ?1
            """, nativeQuery = true)
    List<GetDeliveryAddressByUserIdResponse> getDeliveryAddressByUserId(String userId);
}
