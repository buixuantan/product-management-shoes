package com.cg.controller;

import com.cg.model.Order;
import com.cg.model.Product;
import com.cg.model.dto.OrderDTO;
import com.cg.service.order.IOrderService;
import com.cg.service.order.OrderServiceImpl;
import com.cg.service.product.IProductService;
import com.cg.uploader.CloudinaryConfig;
import com.cg.uploader.UploaderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    CloudinaryConfig uploaderConfig;
    @Autowired
    private IOrderService orderService;

    @GetMapping("/")
    public ModelAndView showHome() {
        ModelAndView modelAndView = new ModelAndView("/product/list-product");
        return modelAndView;
    }

    @GetMapping("/listProductLock")
    public ModelAndView showProductLock() {
        ModelAndView modelAndView = new ModelAndView("/product/list-productLock");
        return modelAndView;
    }

    @GetMapping("/listCategories")
    public ModelAndView showListCategories() {
        ModelAndView modelAndView = new ModelAndView("/category/list-category");
        return modelAndView;
    }

    @GetMapping("/listOrder")
    public ModelAndView showListOrder() {
        ModelAndView modelAndView = new ModelAndView("/order/list-order");
        return modelAndView;
    }

    @GetMapping("/orderItem")
    public ModelAndView createOrder() {
        ModelAndView modelAndView = new ModelAndView("/order/orderItem");
        Order order = new Order();
        order.setTotalBill(BigDecimal.valueOf(0));
        orderService.save(order);
        modelAndView.addObject("order", order);
        return modelAndView;
    }
}
