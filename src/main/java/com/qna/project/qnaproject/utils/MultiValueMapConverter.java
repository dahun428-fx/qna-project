package com.qna.project.qnaproject.utils;

import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class MultiValueMapConverter {
    
    public static MultiValueMap<String, String> convert (Object dto)  {
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ObjectMapper mapper = new ObjectMapper(); 
            Map<String, String> map = mapper.convertValue(dto, new TypeReference<Map<String, String>> () {});
            params.setAll(map);
            return params;
        } catch (Exception e) {
            log.error("MultiValueMapConverter error / can't match dto to mapper. requestParam = {} ", dto, e);
            e.printStackTrace();
        }
        return null;
    }

}
