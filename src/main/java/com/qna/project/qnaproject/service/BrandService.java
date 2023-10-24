package com.qna.project.qnaproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qna.project.qnaproject.dao.BrandDAO;
import com.qna.project.qnaproject.dto.ec_brand.CreateBrandDTO;
import com.qna.project.qnaproject.dto.ec_brand.ReadBrandDTO;
import com.qna.project.qnaproject.model.Brand;
import com.qna.project.qnaproject.utils.ModelUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BrandService {
    
    private BrandDAO brandDAO;

    public ReadBrandDTO saveBrand(CreateBrandDTO dto) {

        Brand brand = ModelUtil.map(dto, Brand.class); 
        brandDAO.upsertBrand(brand);
        return ModelUtil.map(brand, ReadBrandDTO.class);
    }

    public ReadBrandDTO findByBrandCode(String brandCode) {

        Brand foundBrand = brandDAO.findByBrandCode(brandCode);
        if (foundBrand == null) {
            return null;
        }

        return ModelUtil.map(foundBrand, ReadBrandDTO.class);
    }

}
