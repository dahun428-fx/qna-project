package com.qna.project.qnaproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qna.project.qnaproject.dao.QnaDAO;
import com.qna.project.qnaproject.dto.ec_qna.CreateQnaDTO;
import com.qna.project.qnaproject.dto.ec_qna.ReadQnaDTO;
import com.qna.project.qnaproject.model.EcQna;
import com.qna.project.qnaproject.utils.ModelUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QnaClientService {
    

    private final QnaDAO qnaDAO;

    @Transactional
    public ReadQnaDTO createQna(CreateQnaDTO dto) {

        EcQna qna = ModelUtil.map(dto, EcQna.class);
        qnaDAO.createQna(qna);
        return ModelUtil.map(qna, ReadQnaDTO.class);
    }

    public ReadQnaDTO findQnaOneByQnaId(int qnaId) {
        EcQna qna = qnaDAO.findById(qnaId);
        return ModelUtil.map(qna, ReadQnaDTO.class);
    }
}
