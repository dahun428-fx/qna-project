package com.qna.project.qnaproject.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.qna.project.qnaproject.dao.SeriesDAO;
import com.qna.project.qnaproject.dto.ec_series.CreateSeriesDTO;
import com.qna.project.qnaproject.dto.ec_series.UpdateSeriesDTO;
import com.qna.project.qnaproject.model.Series;
import com.qna.project.qnaproject.utils.ModelUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeriesServiceTests {
    @Autowired
    private SeriesDAO seriesDAO;

    @Test
    public void saveSeriesTest() {

        String seriseCode = "123456";
        String seriesName = "test_seriesName";
        String seriesType = "test_seriesType";
        String brandCode = "test_brandCode";
        String categoryCode = "test_ctcode";
        String companyName = "testcompany_name";
        String mvType = "0";
        String vonaType = "0";

        CreateSeriesDTO createSeriesDTO
            =   CreateSeriesDTO
                .builder()
                .seriesCode(seriseCode)
                .seriesName(seriesName)
                .seriesType(seriesType)
                .brandCode(brandCode)
                .categoryCode(categoryCode)
                .companyName(companyName)
                .mvType(mvType)
                .vonaType(vonaType)
                .build();

        Series series = ModelUtil.map(createSeriesDTO, Series.class);

        seriesDAO.upsertSeries(series);

        Series foundSeries = seriesDAO.findBySeriesCode(seriseCode);

        assertEquals(seriseCode, foundSeries.getSeriesCode());
        assertEquals(seriesName, foundSeries.getSeriesName());
        assertEquals(seriesType, foundSeries.getSeriesType());
        assertEquals(brandCode, foundSeries.getBrandCode());
        assertEquals(categoryCode, foundSeries.getCategoryCode());
        assertEquals(companyName, foundSeries.getCompanyName());
        assertEquals(mvType, foundSeries.getMvType());
        assertEquals(vonaType, foundSeries.getVonaType());

        UpdateSeriesDTO updateSeriesDTO = ModelUtil.map(foundSeries, UpdateSeriesDTO.class);
        
        updateSeriesDTO.setSeriesName("update test seriesName");
        updateSeriesDTO.setSeriesType("update test seriesType");
        updateSeriesDTO.setBrandCode("update test bcd");
        updateSeriesDTO.setCategoryCode("update test ccd");
        updateSeriesDTO.setCompanyName("update test cname");
        updateSeriesDTO.setMvType("1");
        updateSeriesDTO.setVonaType("1");

        Series forUpdateSeries = ModelUtil.map(updateSeriesDTO, Series.class);
        seriesDAO.upsertSeries(forUpdateSeries);

        Series updatedSeries = seriesDAO.findBySeriesCode(seriseCode);

        assertEquals(updateSeriesDTO.getSeriesCode(), updatedSeries.getSeriesCode());
        assertEquals(updateSeriesDTO.getSeriesName(), updatedSeries.getSeriesName());
        assertEquals(updateSeriesDTO.getSeriesType(), updatedSeries.getSeriesType());
        assertEquals(updateSeriesDTO.getBrandCode(), updatedSeries.getBrandCode());
        assertEquals(updateSeriesDTO.getCategoryCode(), updatedSeries.getCategoryCode());
        assertEquals(updateSeriesDTO.getCompanyName(), updatedSeries.getCompanyName());
        assertEquals(updateSeriesDTO.getMvType(), updatedSeries.getMvType());
        assertEquals(updateSeriesDTO.getVonaType(), updatedSeries.getVonaType());


    }


}
