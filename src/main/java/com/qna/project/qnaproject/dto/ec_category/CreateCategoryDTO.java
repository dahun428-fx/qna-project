package com.qna.project.qnaproject.dto.ec_category;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
public class CreateCategoryDTO {
    private String categoryCode;
    private String categoryName;
}
