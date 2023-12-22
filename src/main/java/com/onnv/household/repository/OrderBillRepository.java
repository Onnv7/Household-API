package com.onnv.household.repository;

import com.onnv.household.dto.sql.GetAllOrderBillByStatus;
import com.onnv.household.dto.sql.GetAllOrderBillByUserIdAndStatus;
import com.onnv.household.dto.sql.ItemOrderDto;
import com.onnv.household.entity.OrderBillEntity;
import com.onnv.household.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderBillRepository extends JpaRepository<OrderBillEntity, String> {
    @Query(value = """
            select p.id as productId, p.name as productName, pi.url as imageUrl, io.type_name as typeName, io.quantity , io.price , io.cancellation_reason as cancellationReason, io.note
            from order_bill ob
            join item_order io on ob.id = io.order_bill_id
            join product p  on io.product_id = p.id
            join product_image pi on pi.product_id = p.id 
            where ob.id = ?1 and pi.is_thumbnail = true
            """, nativeQuery = true)
    List<ItemOrderDto> getItemOrderListByOrderBillId(String orderBillId);
    @Query(value = """
            WITH LastOrderStatus AS (
                SELECT
                    os.order_bill_id,
                    os.status AS last_status,
                    ROW_NUMBER() OVER (PARTITION BY os.order_bill_id ORDER BY os.created_at DESC) AS rn
                FROM order_status os
            )
            SELECT
                ob.id,
                u.id as customerId,
                u.email,
                ob.total_product_price as totalProductPrice,
                CONCAT_WS(' ', u.last_name, u.first_name) AS customerName,
                los.last_status AS lastOrderStatus,
                ob.created_at as createdAt
            FROM order_bill ob
            JOIN `user` u ON ob.user_id = u.id
            JOIN LastOrderStatus los ON ob.id = los.order_bill_id AND los.rn = 1
            WHERE los.last_status = ?1
            order by ob.created_at desc
            """, nativeQuery = true)
    List<GetAllOrderBillByStatus> getAllOrderBillByStatus(String orderBillStatus);

    @Query(value = """
            WITH LastOrderStatus AS (
                SELECT
                    os.order_bill_id,
                    os.status AS last_status,
                    ROW_NUMBER() OVER (PARTITION BY os.order_bill_id ORDER BY os.created_at DESC) AS rn
                FROM order_status os
            )
            SELECT
                ob.id,
                ob.total_product_price as totalProductPrice,
                ob.created_at as createdAt,
                ob.total_bill as totalBill,
                t.status as transactionStatus
            FROM order_bill ob
            JOIN `user` u ON ob.user_id = u.id
            JOIN LastOrderStatus los ON ob.id = los.order_bill_id AND los.rn = 1
            left outer join  transaction t on t.id = ob.transaction_id
            WHERE los.last_status = ?2 and u.id = ?1
            order by ob.created_at desc
            """, nativeQuery = true)
    List<GetAllOrderBillByUserIdAndStatus> getAllOrderBillByUserIdAndStatus(String userId, String orderStatus);
}
