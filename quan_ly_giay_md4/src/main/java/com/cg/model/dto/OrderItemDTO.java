package com.cg.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemDTO {

    private Long id;

    private Long idProduct;

    private String nameProduct;

    private String categoryName;

    private Long idOrder;

    private BigDecimal price;

    private int quantity;

    private BigDecimal total;


}
