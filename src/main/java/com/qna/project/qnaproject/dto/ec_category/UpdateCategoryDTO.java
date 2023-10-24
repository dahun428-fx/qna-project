package com.qna.project.qnaproject.dto.ec_category;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class UpdateCategoryDTO {
    private String categoryCode;
    private String categoryName;
}
