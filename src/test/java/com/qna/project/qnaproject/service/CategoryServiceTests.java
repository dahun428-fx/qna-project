package com.qna.project.qnaproject.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qna.project.qnaproject.dao.CategoryDAO;
import com.qna.project.qnaproject.dto.ec_category.CreateCategoryDTO;
import com.qna.project.qnaproject.dto.ec_category.UpdateCategoryDTO;
import com.qna.project.qnaproject.model.Category;
import com.qna.project.qnaproject.utils.ModelUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTests {
    
    @Autowired
    private CategoryDAO categoryDAO;

    @Test
    public void saveCategory() {

        String categoryCode = "test category code";
        String categoryName = "test category name";

        CreateCategoryDTO createCategoryDTO
                = CreateCategoryDTO
                    .builder()
                    .categoryCode(categoryCode)
                    .categoryName(categoryName)
                    .build();

        Category category = ModelUtil.map(createCategoryDTO, Category.class);
        categoryDAO.upsertCategory(category);

        Category foundCategory = categoryDAO.findByCategoryCode(categoryCode);

        assertEquals(categoryCode, foundCategory.getCategoryCode());
        assertEquals(categoryName, foundCategory.getCategoryName());

        UpdateCategoryDTO updateCategoryDTO = ModelUtil.map(foundCategory, UpdateCategoryDTO.class);

        updateCategoryDTO.setCategoryName("update category name");
        Category forUpdateCategory = ModelUtil.map(updateCategoryDTO, Category.class);
        categoryDAO.upsertCategory(forUpdateCategory);

        Category updatedCategory = categoryDAO.findByCategoryCode(categoryCode);

        assertEquals(updateCategoryDTO.getCategoryCode(), updatedCategory.getCategoryCode());
        assertEquals(updateCategoryDTO.getCategoryName(), updatedCategory.getCategoryName());

    }

}
