package com.cg.service.category;

import com.cg.model.Category;
import com.cg.model.dto.CategoryDTO;
import com.cg.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDTO> findAllCategoriesDTO() {
        return categoryRepository.findAllCategoriesDTO();
    }

    @Override
    public List<CategoryDTO> findAllCategoriesDTOLock() {
        return categoryRepository.findAllCategoriesDTOLock();
    }

    @Override
    public CategoryDTO findCategoryDTOById(Long id) {
        return categoryRepository.findCategoryDTOById(id);
    }
}
