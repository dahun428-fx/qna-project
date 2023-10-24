package com.qna.project.qnaproject.dto.ec_brand;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
public class CreateBrandDTO {
    
    private String brandCode;
    private String brandName;
}
