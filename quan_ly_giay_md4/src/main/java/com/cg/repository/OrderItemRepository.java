package com.cg.repository;

import com.cg.model.OrderItem;
import com.cg.model.dto.OrderItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT new com.cg.model.dto.OrderItemDTO(" +
            "ot.id,ot.product.idProduct,ot.product.nameProduct,ot.product.category.nameCategory,ot.order.id,ot.price,ot.quantity,ot.totalPrice)" +
            "FROM OrderItem ot " +
            "WHERE ot.deleted = false " +
            "AND ot.order.id=:id")
    List<OrderItemDTO> findAllOrderItemDTO(@Param("id") Long id);

    @Query("SELECT new com.cg.model.dto.OrderItemDTO(" +
            "ot.id,ot.product.idProduct,ot.product.nameProduct,ot.product.category.nameCategory,ot.order.id,ot.price,ot.quantity,ot.totalPrice)" +
            "FROM OrderItem ot " +
            "WHERE ot.deleted = false " +
            "AND ot.id=:id")
    Optional<OrderItemDTO> findOrderItemDTOById(@Param("id") Long id);

    @Procedure(procedureName = "totalBillBeforePaid")
    BigDecimal totalBillBeforePaid(@Param("id") Long id);

//    @Query("DELETE FROM OrderItem ot WHERE ot.order.id = :id")
//    void deleteOrderItemByOrderId(@Param("id") Long id);

    void deleteAllByOrderId(Long id);


}

