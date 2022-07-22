package com.cg.service.category;

import com.cg.model.Category;
import com.cg.model.dto.CategoryDTO;
import com.cg.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryService extends IGeneralService<Category> {

    List<CategoryDTO> findAllCategoriesDTO();

    List<CategoryDTO> findAllCategoriesDTOLock();

    CategoryDTO findCategoryDTOById(@Param("id") Long id);

}
