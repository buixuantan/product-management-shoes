package com.cg.service.orderDetail;

import com.cg.model.OrderItem;
import com.cg.model.dto.OrderItemDTO;
import com.cg.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class OrderItemServiceImpl implements IOrderItemService{

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void remove(Long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public List<OrderItemDTO> findAllOrderItemDTO(Long id) {
        return orderItemRepository.findAllOrderItemDTO(id);
    }

    @Override
    public Optional<OrderItemDTO> findOrderItemDTOById(Long id) {
        return orderItemRepository.findOrderItemDTOById(id);
    }

    @Override
    public BigDecimal totalBillBeforePaid(Long id) {
        return orderItemRepository.totalBillBeforePaid(id);
    }

    @Override
    public void deleteAllByOrderId(Long id) {
        orderItemRepository.deleteAllByOrderId(id);
    }
}
