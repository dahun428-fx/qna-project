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
@Alias(value = "ec_category")
public class Category {
    
    private String categoryCode;
    private String categoryName;

    public static Category createCategory(String categoryCode, String categoryName){
        if(categoryCode.isEmpty() || categoryName.isEmpty()) {
            return null;
        }
        return new Category(categoryCode, categoryName);
    }
}
