package com.cg.service.imageProduct;

import com.cg.model.ImageProduct;
import com.cg.repository.ImageProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImageProductServiceImpl implements ImageProductService {

    @Autowired
    private ImageProductRepository imageProductRepository;

    @Override
    public List<ImageProduct> findAll() {
        return imageProductRepository.findAll();
    }

    @Override
    public Optional<ImageProduct> findById(Long id) {
        return imageProductRepository.findById(id);
    }

    @Override
    public ImageProduct save(ImageProduct imageProduct) {
        return imageProductRepository.save(imageProduct);
    }

    @Override
    public void remove(Long id) {
        imageProductRepository.deleteById(id);
    }
}
