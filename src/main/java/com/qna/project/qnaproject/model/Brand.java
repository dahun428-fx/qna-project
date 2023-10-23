package com.qna.project.qnaproject.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Alias(value = "ec_brand")
@NoArgsConstructor
@ToString
public class Brand {

    private String brandCode;
    private String brandName;
    
}
