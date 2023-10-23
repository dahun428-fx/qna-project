package com.qna.project.qnaproject.dao;

import org.apache.ibatis.annotations.Mapper;

import com.qna.project.qnaproject.model.Category;

@Mapper
public interface CategoryDAO {
    void upsertCategory(Category category);
}
