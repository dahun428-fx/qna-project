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

    public static Series createSeries(String seriesCode, String seriesName, String categoryCode, String brandCode){
        if(seriesCode.isEmpty() || seriesName.isEmpty() || categoryCode.isEmpty() || brandCode.isEmpty()) {
            return null;
        }
        return new Series(seriesCode, seriesName, "", "", "", "", categoryCode, brandCode);
    }

}
