package com.cg.controller.restAPI;

import com.cg.exception.DataInputException;
import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.ProductDTOClient;
import com.cg.service.category.ICategoryService;
import com.cg.service.product.IProductService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private AppUtils appUtils;


    @GetMapping("/showProduct")
    public ResponseEntity<?> findAll() {
        List<ProductDTOClient> productDTOS = productService.findAllProductDTO();

        if (productDTOS.isEmpty()) {
            return new ResponseEntity<>(productDTOS,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @GetMapping("/lockProduct")
    public ResponseEntity<?> findAllLockProduct() {
        List<ProductDTO> productDTOS = productService.findAllProductLock();
        if (productDTOS.isEmpty()) {
            System.out.println(productDTOS);
            return new ResponseEntity<>(productDTOS,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<ProductDTO> productDTO = productService.findProductDTOById(id);

        if (productDTO.isPresent()) {
            return new ResponseEntity<>(productDTO.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Can not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> save(@Validated ProductDTO productDTO,
                                  BindingResult result) {
        if (result.hasFieldErrors()) {
            System.out.println("lỗi");
            return appUtils.mapErrorToResponse(result);

        }

        try {
            System.out.println(productDTO.getId());
            System.out.println(productDTO.getNameProduct());
            System.out.println(productDTO.getFile());
//        productDTO.setId(0);
            Product product = productService.createProduct(productDTO);
//        product.setNameProduct(productDTO.getNameProduct());
//        product.setPrice(productDTO.getPrice());
//        product.setCategory(categoryService.findById(productDTO.getCategory()).get());
//        product.setDescription(productDTO.getDescription());
            productDTO.setId(product.getIdProduct());
            System.out.println(productDTO);
            return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new DataInputException("Product creation information is not valid, please check the information again");
        }

    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Long id,
                                     @RequestBody ProductDTO productDTO) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            product.get().setNameProduct(productDTO.getNameProduct());
            product.get().setPrice(productDTO.getPrice());
            product.get().setDescription(productDTO.getDescription());
            productService.save(product.get());
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>("Not found product", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> suspension(@PathVariable("id") Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            product.get().setDeleted(true);
            productService.save(product.get());
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.get().getIdProduct());
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>("Not found product", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/unSuspension/{id}")
    public ResponseEntity<?> unSuspension(@PathVariable("id") Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            product.get().setDeleted(false);
            productService.save(product.get());
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.get().getIdProduct());
            productDTO.setNameProduct(product.get().getNameProduct());
            productDTO.setPrice(product.get().getPrice());
            productDTO.setDescription(product.get().getDescription());
            return new ResponseEntity<>(productDTO, HttpStatus.OK);

        }
        return new ResponseEntity<>("Can not find product", HttpStatus.NOT_FOUND);
    }
}
