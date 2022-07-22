package com.cg.model.dto;

import com.cg.model.ImageProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {

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

    private MultipartFile file;

    public ImageProduct toImageProduct() {
        return new ImageProduct()
                .setFileName(fileName)
                .setFileFolder(fileFolder)
                .setFileUrl(fileUrl)
                .setCloudId(cloudId);
    }


}
