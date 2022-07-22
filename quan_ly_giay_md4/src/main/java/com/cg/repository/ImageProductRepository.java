package com.cg.repository;

import com.cg.model.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageProductRepository extends JpaRepository<ImageProduct, Long> {
}
