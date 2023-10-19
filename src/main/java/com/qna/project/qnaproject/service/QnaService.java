package com.qna.project.qnaproject.service;

import org.springframework.stereotype.Service;

import com.qna.project.qnaproject.dao.QnaDAO;
import com.qna.project.qnaproject.dto.CreateQnaDTO;
import com.qna.project.qnaproject.model.EcQna;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class QnaService {
    

    private final QnaDAO qnaDAO;

    public void createQna(CreateQnaDTO dto) {
        EcQna qna = EcQna.builder().build();
        qnaDAO.createQna(qna);
    }

}
