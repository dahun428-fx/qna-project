package com.qna.project.qnaproject.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qna.project.qnaproject.dao.BrandDAO;
import com.qna.project.qnaproject.dto.ec_brand.CreateBrandDTO;
import com.qna.project.qnaproject.dto.ec_brand.UpdateBrandDTO;
import com.qna.project.qnaproject.model.Brand;
import com.qna.project.qnaproject.utils.ModelUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrandServiceTests {
    
    @Autowired
    private BrandDAO brandDAO;

    @Test
    public void saveBrandTest(){

        String brandCode = "test Brand Code";
        String brandName = "test Brand Name";

        CreateBrandDTO createBrandDTO 
                    = CreateBrandDTO
                        .builder()
                        .brandCode(brandCode)
                        .brandName(brandName)
                        .build();

        Brand brand = ModelUtil.map(createBrandDTO, Brand.class);

        brandDAO.upsertBrand(brand);

        Brand foundBrand = brandDAO.findByBrandCode(brandCode);

        assertEquals(brandCode, foundBrand.getBrandCode());
        assertEquals(brandName, foundBrand.getBrandName());

        UpdateBrandDTO updateBrandDTO = ModelUtil.map(foundBrand, UpdateBrandDTO.class);

        updateBrandDTO.setBrandName("updated new brandName");
        Brand forUpdateBrand = ModelUtil.map(updateBrandDTO, Brand.class);
        brandDAO.upsertBrand(forUpdateBrand);

        Brand updatedBrand = brandDAO.findByBrandCode(brandCode);

        assertEquals(updateBrandDTO.getBrandCode(), updatedBrand.getBrandCode());
        assertEquals(updateBrandDTO.getBrandName(), updatedBrand.getBrandName());

    }


}
