package com.qna.project.qnaproject.dao;

import org.apache.ibatis.annotations.Mapper;

import com.qna.project.qnaproject.dto.ec_qna.ReadQnaDTO;
import com.qna.project.qnaproject.model.EcQna;

@Mapper
public interface QnaDAO {
    void createQna(EcQna qna);
    EcQna findById(int qnaId);
    ReadQnaDTO findByIdJoinWithSeriesCategoryBrand(int qnaId);
}
