package com.qna.project.qnaproject.dto.ec_brand;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ReadBrandDTO {
    
    private String brandCode;
    private String brandName;
}
