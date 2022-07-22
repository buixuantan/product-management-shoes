package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @NotBlank(message = "This field can not empty")
    @Size(min = 5, max = 40, message = "The name product have to between 5 to 40")
    private String nameProduct;

    @Digits(integer = 12,fraction = 0)
    private BigDecimal price;

    private boolean deleted = false;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "idCategory")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "image_product_id")
    private ImageProduct imageProduct;

}
