package com.cg.service.order;

import com.cg.model.Order;
import com.cg.model.OrderItem;
import com.cg.model.dto.OrderDTO;
import com.cg.repository.OrderItemRepository;
import com.cg.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void remove(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDTO> findAllOrderDTO() {
        return orderRepository.findAllOrderDTO();
    }

    @Override
    public Optional<OrderDTO> findOrderDTOById(Long id) {
        return orderRepository.findOrderDTOById(id);
    }

    @Override
    public void cancelOrder(Long id) {
        orderItemRepository.deleteAllByOrderId(id);
        orderRepository.deleteById(id);
    }
}
