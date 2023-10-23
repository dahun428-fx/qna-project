package com.qna.project.qnaproject.model;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Alias(value = "ec_series")
public class Series {
    
    private String seriesCode;
    private String seriesName;
    private String companyName;
    private String mvType;
    private String vonaType;
    private String seriesType;
    private String categoryCode;
    private String brandCode;
}
