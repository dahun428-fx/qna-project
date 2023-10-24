package com.qna.project.qnaproject.model;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Alias(value = "ec_brand")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Brand {

    private String brandCode;
    private String brandName;

    public static Brand createBrand(String brandCode, String brandName) {
        if(brandCode.isEmpty() || brandName.isEmpty()) {
            return null;
        }
        return new Brand(brandCode, brandName);
    }
    
}
