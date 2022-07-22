package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @NotBlank(message = "This field can not empty")
    @Size(min = 5, max = 40, message = "The name of category have to between 5 to 40")
    private String nameCategory;

    @OneToMany(targetEntity = Product.class, mappedBy = "category")
    List<Product> products;
}
