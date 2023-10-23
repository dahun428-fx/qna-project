package com.qna.project.qnaproject.dto.ec_series;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
public class CreateSeriesDTO {
    private String seriesCode;
    private String seriesName;
    private String companyName;
    private String mvType;
    private String vonaType;
    private String seriesType;
    private String categoryCode;
    private String brandCode;
}
