package com.qna.project.qnaproject.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qna.project.qnaproject.dao.BrandDAO;
import com.qna.project.qnaproject.dao.CategoryDAO;
import com.qna.project.qnaproject.dao.QnaDAO;
import com.qna.project.qnaproject.dao.SeriesDAO;
import com.qna.project.qnaproject.dto.ec_qna.CreateQnaDTO;
import com.qna.project.qnaproject.dto.ec_qna.ReadQnaDTO;
import com.qna.project.qnaproject.model.Brand;
import com.qna.project.qnaproject.model.Category;
import com.qna.project.qnaproject.model.EcQna;
import com.qna.project.qnaproject.model.Series;
import com.qna.project.qnaproject.utils.ModelUtil;


@RunWith(SpringRunner.class)
// @TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest
public class QnaClientServiceTests {
    @Autowired
    private QnaDAO qnaDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private BrandDAO brandDAO;
    @Autowired
    private SeriesDAO seriesDAO;

    @Value("classpath:test-dummy-data/create-qna-data.json")
    private Resource jsonFile;

    private CreateQnaDTO createQnaDTO = null;

    @Before
    public void setUp(){
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map<String, String> map = mapper.readValue(jsonFile.getFile(), Map.class);
            createQnaDTO = ModelUtil.map(map, CreateQnaDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    // @Transactional
    @Test
    public void createQnaTest() {

        CreateQnaDTO dto 
            = createQnaDTO;
        EcQna qna = ModelUtil.map(dto, EcQna.class);
        Series series = Series.createSeries(dto.getSeriesCode(), dto.getSeriesName(), dto.getCategoryCode(), dto.getBrandCode());
        Brand brand = Brand.createBrand(dto.getBrandCode(), dto.getBrandName());
        Category category = Category.createCategory(dto.getCategoryCode(), dto.getCategoryName());

        System.out.println(series);
        
        qnaDAO.createQna(qna);
        categoryDAO.upsertCategory(category);
        brandDAO.upsertBrand(brand);
        seriesDAO.upsertSeries(series);

        EcQna foundQna = qnaDAO.findById(qna.getQnaId());
        assertEquals(dto.getTitle(), foundQna.getTitle());
        assertEquals(dto.getContent(), foundQna.getContent());
        assertEquals(dto.getPartNo(), foundQna.getPartNo());
        assertEquals(dto.getRegId(), foundQna.getRegId());
        assertEquals(dto.getSeriesCode(), foundQna.getSeriesCode());
        assertEquals(dto.getContactId(), foundQna.getContactId());
        
        Series foundSeries = seriesDAO.findBySeriesCode(dto.getSeriesCode());
        assertEquals(dto.getSeriesCode(), foundSeries.getSeriesCode());
        assertEquals(dto.getSeriesName(), foundSeries.getSeriesName());
        assertEquals(dto.getCategoryCode(), foundSeries.getCategoryCode());
        assertEquals(dto.getBrandCode(), foundSeries.getBrandCode());
        
        Brand foundBrand = brandDAO.findByBrandCode(dto.getBrandCode());
        assertEquals(dto.getBrandCode(), foundBrand.getBrandCode());
        assertEquals(dto.getBrandName(), foundBrand.getBrandName());

        Category foundCategory = categoryDAO.findByCategoryCode(dto.getCategoryCode());
        assertEquals(dto.getCategoryCode(), foundCategory.getCategoryCode());
        assertEquals(dto.getCategoryName(), foundCategory.getCategoryName());

    }

    @Test
    public void findQnaOneByQnaIdTest(){

        int qnaId = 1;
        ReadQnaDTO qnaDTO = qnaDAO.findByIdJoinWithSeriesCategoryBrand(qnaId);

        assertEquals(createQnaDTO.getQnaId(), qnaDTO.getQnaId());
        assertEquals(createQnaDTO.getTitle(), qnaDTO.getTitle());
        assertEquals(createQnaDTO.getContent(), qnaDTO.getContent());
        assertEquals(createQnaDTO.getBrandCode(), qnaDTO.getBrandCode());
        assertEquals(createQnaDTO.getBrandName(), qnaDTO.getBrandName());
        assertEquals(createQnaDTO.getCategoryCode(), qnaDTO.getCategoryCode());
        assertEquals(createQnaDTO.getCategoryName(), qnaDTO.getCategoryName());
        assertEquals(createQnaDTO.getContactId(), qnaDTO.getContactId());
        assertEquals(createQnaDTO.getRegId(), qnaDTO.getRegId());
        assertEquals(createQnaDTO.getPartNo(), qnaDTO.getPartNo());
        assertEquals(createQnaDTO.getIsDelete(), qnaDTO.getIsDelete());
        assertEquals(createQnaDTO.getDeletedUserId(), qnaDTO.getDeletedUserId());
        assertEquals(createQnaDTO.getDeletedDate(), qnaDTO.getDeletedDate());
        assertEquals(createQnaDTO.getIsBlind(), qnaDTO.getIsBlind());


    }

}
