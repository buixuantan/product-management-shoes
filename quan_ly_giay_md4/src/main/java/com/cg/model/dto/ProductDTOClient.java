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
public class ProductDTOClient {

    private Long id;

    private String nameProduct;

    private BigDecimal price;

    private Long category;

    private String categoryName;

    private String description;

    private String fileName;

    private String fileFolder;

    private String fileUrl;

    private String cloudId;
}
