package com.qna.project.qnaproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qna.project.qnaproject.dto.ec_qna.CreateQnaDTO;
import com.qna.project.qnaproject.dto.ec_qna.ReadQnaDTO;
import com.qna.project.qnaproject.service.QnaClientService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/q1/c1/api/qna")
public class QnaClientController {
    

    private final QnaClientService qnaClientService;

    @GetMapping("/home")
    public String Hello () {
        return "Hello";
    }

    @PostMapping(value="/create")
    public ResponseEntity<ReadQnaDTO> create(@RequestBody CreateQnaDTO entity) {

        ReadQnaDTO createdQna = qnaClientService.createQna(entity);
        log.debug("/create accept : "+createdQna);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQna);
    }
    
}
