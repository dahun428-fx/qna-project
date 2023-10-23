package com.qna.project.qnaproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qna.project.qnaproject.dao.SeriesDAO;
import com.qna.project.qnaproject.dto.ec_series.CreateSeriesDTO;
import com.qna.project.qnaproject.dto.ec_series.ReadSeriesDTO;
import com.qna.project.qnaproject.model.Series;
import com.qna.project.qnaproject.utils.ModelUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SeriesService {
    
    private SeriesDAO seriesDAO;

    @Transactional
    public ReadSeriesDTO saveSeries(CreateSeriesDTO createSeriesDTO) {
        
        Series series = ModelUtil.map(createSeriesDTO, Series.class);
        seriesDAO.upsertSeries(series);
        return ModelUtil.map(series, ReadSeriesDTO.class);
    }

    public ReadSeriesDTO findBySeriesCode(String seriesCode) {

        Series foundSeries = seriesDAO.findBySeriesCode(seriesCode);
        if (foundSeries == null) {
            return null;
        }
        return ModelUtil.map(foundSeries, ReadSeriesDTO.class);
    }

}
