package com.qna.project.qnaproject.dao;

import org.apache.ibatis.annotations.Mapper;

import com.qna.project.qnaproject.model.Series;

@Mapper
public interface SeriesDAO {
    void upsertSeries(Series series);
    Series findBySeriesCode(String seriesCode);
}
