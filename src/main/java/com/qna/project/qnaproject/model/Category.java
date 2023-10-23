package com.qna.project.qnaproject.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@Alias(value = "ec_category")
public class Category {
    
    private String categoryCode;
    private String categoryName;
}
