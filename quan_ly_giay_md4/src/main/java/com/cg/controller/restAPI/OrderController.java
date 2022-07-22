package com.cg.controller.restAPI;

import com.cg.model.Order;
import com.cg.model.OrderItem;
import com.cg.model.Product;
import com.cg.model.dto.OrderDTO;
import com.cg.model.dto.OrderItemDTO;
import com.cg.service.order.IOrderService;
import com.cg.service.orderDetail.IOrderItemService;
import com.cg.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderItemService orderItemService;

    @GetMapping("/showOrders")
    public ResponseEntity<?> showListOrder() {
        List<OrderDTO> orderDTOS = orderService.findAllOrderDTO();
        if (orderDTOS.isEmpty()) {
            return new ResponseEntity<>("Can not found order", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);
    }

    @GetMapping("/showOrderItems/{id}")
    public ResponseEntity<?> showOrderItems(@PathVariable("id") Long id) {
        List<OrderItemDTO> orderItemDTOS = orderItemService.findAllOrderItemDTO(id);
//        if(orderItemDTOS.isEmpty()) {
//            return new ResponseEntity<> ("Can not found order item", HttpStatus.NOT_FOUND);
//        }

        return new ResponseEntity<> (orderItemDTOS, HttpStatus.OK);
    }

    @GetMapping("/totalBillBeforePaid/{id}")
    public ResponseEntity<?> totalBillBeforePaid(@PathVariable("id") Long id) {
        BigDecimal totalBill = orderItemService.totalBillBeforePaid(id);

        return new ResponseEntity<>(totalBill,HttpStatus.OK);
    }

    @PostMapping("/createOrderItem")
    public ResponseEntity<?> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        List<OrderItemDTO> orderDTOS = orderItemService.findAllOrderItemDTO(orderItemDTO.getIdOrder());
        for (OrderItemDTO orderItem : orderDTOS) {
            if (orderItem.getIdProduct() == orderItemDTO.getIdProduct()) {
                orderItem.setQuantity( orderItem.getQuantity() + 1);
                orderItem.setTotal(orderItem.getTotal().add(orderItemDTO.getTotal()));
                OrderItem orderItemUpdate = orderItemService.findById(orderItem.getId()).get();
                orderItemUpdate.setTotalPrice(orderItem.getTotal());
                orderItemUpdate.setQuantity(orderItem.getQuantity());

                orderItemService.save(orderItemUpdate);

                orderItemDTO.setId(orderItemUpdate.getId());
                orderItemDTO.setNameProduct(orderItem.getNameProduct());
                orderItemDTO.setPrice(orderItem.getPrice());
                orderItemDTO.setCategoryName(orderItemDTO.getCategoryName());
                orderItemDTO.setQuantity(orderItemUpdate.getQuantity());
                orderItemDTO.setTotal(orderItemUpdate.getTotalPrice());

                return new ResponseEntity<>(orderItemDTO, HttpStatus.OK);
            }
        }

        Optional<Product> product = productService.findById(orderItemDTO.getIdProduct());

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(orderService.findById(orderItemDTO.getIdOrder()).get());
        orderItem.setPrice(product.get().getPrice());
        orderItem.setProduct(product.get());
        orderItem.setQuantity(1);
        orderItem.setTotalPrice(orderItem.getPrice());
        orderItemService.save(orderItem);

        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setNameProduct(orderItem.getProduct().getNameProduct());
        orderItemDTO.setPrice(orderItem.getPrice());
        orderItemDTO.setCategoryName(product.get().getCategory().getNameCategory());
        orderItemDTO.setQuantity(1);
        orderItemDTO.setTotal(orderItem.getTotalPrice());

        return new ResponseEntity<>(orderItemDTO, HttpStatus.CREATED);
    }

   @PutMapping("/removeOrderItem/{id}")
   public ResponseEntity<?> removeOrderItem(@PathVariable("id") Long id) {
        Optional<OrderItemDTO> orderItemDTO = orderItemService.findOrderItemDTOById(id);

        if(orderItemDTO.isPresent()) {
            if(orderItemDTO.get().getQuantity() == 1) {
                Optional<OrderItem> orderItem = orderItemService.findById(id);
                orderItemDTO.get().setId(orderItem.get().getId());
                orderItemDTO.get().setQuantity(0);
                orderItemService.remove(id);
                OrderItemDTO orderItemDTO1 = new OrderItemDTO();


                return new ResponseEntity<> (orderItemDTO.get(), HttpStatus.OK);
            } else {

                Optional<OrderItem> orderItem = orderItemService.findById(id);
                int currentQuantity = orderItem.get().getQuantity();
                BigDecimal price = orderItem.get().getPrice();
                orderItem.get().setQuantity(currentQuantity - 1);
                orderItem.get().setTotalPrice(price.multiply(BigDecimal.valueOf(currentQuantity)));
                orderItemService.save(orderItem.get());
                orderItemDTO.get().setId(orderItem.get().getId());
                orderItemDTO.get().setQuantity(orderItem.get().getQuantity());
                orderItemDTO.get().setTotal(orderItem.get().getTotalPrice());
                return new ResponseEntity<> (orderItemDTO.get(), HttpStatus.OK);
            }

        }
        return new ResponseEntity<> ("Can not found orderItem", HttpStatus.BAD_REQUEST);
   }

    @PutMapping("/createOrder")
    public ResponseEntity<?> paidOrder(@RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setTotalBill(orderDTO.getTotalBill());
        orderService.save(order);
        return new ResponseEntity<>(order,HttpStatus.CREATED);
    }

    @DeleteMapping("/removeOrder/{id}")
    public ResponseEntity<?> removeOrder(@PathVariable("id") Long id) {
        Optional<Order> order = orderService.findById(id);
        if(order.isPresent()) {
            orderService.cancelOrder(id);
            return new ResponseEntity<>("Remove order successful", HttpStatus.OK);
        }

        return new ResponseEntity<>("Can not found order", HttpStatus.NOT_FOUND);
    }

}
