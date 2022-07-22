package com.cg.repository;

import com.cg.model.Order;
import com.cg.model.dto.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT " +
            "new com.cg.model.dto.OrderDTO(" +
            "o.id,o.totalBill,o.createdAt) " +
            "FROM Order o " +
            "WHERE o.deleted = false ")
    List<OrderDTO> findAllOrderDTO();

    @Query("SELECT " +
            "new com.cg.model.dto.OrderDTO(" +
            "o.id,o.totalBill,o.createdAt) " +
            "FROM Order o " +
            "WHERE o.deleted = false " +
            "AND o.id =: id")
    Optional<OrderDTO> findOrderDTOById(@Param("id") Long id);
}
