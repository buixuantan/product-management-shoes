package com.cg.service.orderDetail;

import com.cg.model.OrderItem;
import com.cg.model.dto.OrderItemDTO;
import com.cg.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IOrderItemService extends IGeneralService<OrderItem> {

    List<OrderItemDTO> findAllOrderItemDTO(Long id);

    Optional<OrderItemDTO> findOrderItemDTOById(Long id);

    BigDecimal totalBillBeforePaid(Long id);

    void deleteAllByOrderId(Long id);

}
