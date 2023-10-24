package com.qna.project.qnaproject.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.qna.project.qnaproject.dao.BrandDAO;
import com.qna.project.qnaproject.dao.CategoryDAO;
import com.qna.project.qnaproject.dao.QnaDAO;
import com.qna.project.qnaproject.dao.SeriesDAO;
import com.qna.project.qnaproject.dto.ec_qna.CreateQnaDTO;
import com.qna.project.qnaproject.dto.ec_qna.ReadQnaDTO;
import com.qna.project.qnaproject.dto.ec_series.CreateSeriesDTO;
import com.qna.project.qnaproject.model.Brand;
import com.qna.project.qnaproject.model.Category;
import com.qna.project.qnaproject.model.EcQna;
import com.qna.project.qnaproject.model.Series;
import com.qna.project.qnaproject.utils.ModelUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class QnaClientService {
    

    private final QnaDAO qnaDAO;
    private final SeriesDAO seriesDAO;
    private final BrandDAO brandDAO;
    private final CategoryDAO categoryDAO;

    @Transactional
    public ReadQnaDTO createQna(CreateQnaDTO dto) {

        EcQna qna = ModelUtil.map(dto, EcQna.class);
        
        Series series = Series.createSeries(dto.getSeriesCode(), dto.getSeriesName(), dto.getCategoryCode(), dto.getBrandCode());
        Brand brand = Brand.createBrand(dto.getBrandCode(), dto.getBrandName());
        Category category = Category.createCategory(dto.getCategoryCode(), dto.getCategoryName());        

        if ( series == null || brand == null || category == null) {
            log.info("please check paramter : ", series, brand, category);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "PARAMETER NOT AVAILABLE :: Parameters Not Acceptable");
        }
        qnaDAO.createQna(qna);
        seriesDAO.upsertSeries(series);
        brandDAO.upsertBrand(brand);
        categoryDAO.upsertCategory(category);

        return ModelUtil.map(dto, ReadQnaDTO.class);
    }

    public ReadQnaDTO findQnaOneByQnaId(int qnaId) {
        return qnaDAO.findByIdJoinWithSeriesCategoryBrand(qnaId);
    }
}
