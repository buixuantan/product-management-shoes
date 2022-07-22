package com.cg.service.product;

import com.cg.model.ImageProduct;
import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.ProductDTOClient;
import com.cg.repository.ProductRepository;
import com.cg.service.category.ICategoryService;
import com.cg.service.imageProduct.ImageProductService;
import com.cg.service.uploadImage.IUploadImageProductService;
import com.cg.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private UploadUtils utils;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageProductService imageProductService;

    @Autowired
    private IUploadImageProductService uploadImageProductService;

    @Autowired
    private ICategoryService categoryService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        ImageProduct imageProduct = productDTO.toImageProduct();
        imageProduct.setFileName(productDTO.getFile().getOriginalFilename());
        System.out.println(imageProduct);
        System.out.println("lỗi ở đây _1 !");
        product.setNameProduct(productDTO.getNameProduct());
        product.setPrice(productDTO.getPrice());
        product.setCategory(categoryService.findById(productDTO.getCategory()).get());
        product.setDescription(productDTO.getDescription());
        product.setImageProduct(imageProduct);
        uploadAndSaveProductImage(productDTO, imageProduct);
        productRepository.save(product);
        System.out.println(product);

        return product;
    }

    public void uploadAndSaveProductImage(ProductDTO productDTO, ImageProduct imageProduct) {
        try {
            Map uploadResult = uploadImageProductService.uploadImage(productDTO.getFile(), utils.buildImageUploadParams(imageProduct));
            String fileUrl = (String) uploadResult.get("secure_url");
//            String fileFormat = (String) uploadResult.get("format");

            System.out.println("lỗi ở đây _2 !");
//            imageProduct.setFileName(imageProduct.getId() + "." + fileFormat);
            imageProduct.setFileUrl(fileUrl);
            imageProduct.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
            imageProduct.setCloudId(imageProduct.getFileFolder() + "/" + imageProduct.getId());
            imageProductService.save(imageProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductDTOClient> findAllProductDTO() {
        return productRepository.findAllProductDTO();

    }

    @Override
    public List<ProductDTO> findAllProductLock() {
        return productRepository.findAllProductLock();

    }

    @Override
    public Optional<ProductDTO> findProductDTOById(Long id) {
        return productRepository.findProductDTOById(id);

    }
}
