package com.qna.project.qnaproject.service;

import org.springframework.stereotype.Service;

import com.qna.project.qnaproject.dao.CategoryDAO;
import com.qna.project.qnaproject.dto.ec_category.CreateCategoryDTO;
import com.qna.project.qnaproject.dto.ec_category.ReadCategoryDTO;
import com.qna.project.qnaproject.model.Category;
import com.qna.project.qnaproject.utils.ModelUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
    
    private CategoryDAO categoryDAO;

    public ReadCategoryDTO saveCategory(CreateCategoryDTO dto) {

        Category category = ModelUtil.map(dto, Category.class);
        categoryDAO.upsertCategory(category);

        return ModelUtil.map(category, ReadCategoryDTO.class);
    }

    public ReadCategoryDTO findByCategoryCode(String categoryCode) {
        return ModelUtil.map(categoryDAO.findByCategoryCode(categoryCode), ReadCategoryDTO.class);
    }
}
