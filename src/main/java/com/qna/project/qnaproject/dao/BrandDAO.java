package com.qna.project.qnaproject.dao;

import org.apache.ibatis.annotations.Mapper;

import com.qna.project.qnaproject.model.Brand;

@Mapper
public interface BrandDAO {
    void upsertBrand(Brand brand);
    Brand findByBrandCode(String brandCode);
}
