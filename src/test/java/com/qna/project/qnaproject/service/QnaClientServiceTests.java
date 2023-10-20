package com.qna.project.qnaproject.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.qna.project.qnaproject.dao.QnaDAO;
import com.qna.project.qnaproject.dto.ec_qna.CreateQnaDTO;
import com.qna.project.qnaproject.model.EcQna;
import com.qna.project.qnaproject.utils.ModelUtil;


@RunWith(SpringRunner.class)
// @TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest
public class QnaClientServiceTests {
    @Autowired
    private QnaDAO qnaDAO;

    @Test
    public void createQnaTest() {

        String title = "testTitle";
        String content = "testContent";
        String partNo = "partNoTest";
        String regId = "username12";
        String seriesCode = "seriesCode__test";
        int contactId = 1234;

        CreateQnaDTO dto 
            = CreateQnaDTO
                .builder()
                .title(title)
                .content(content)
                .partNo(partNo)
                .regId(regId)
                .seriesCode(seriesCode)
                .contactId(contactId)
                .build();
        EcQna qna = ModelUtil.map(dto, EcQna.class);
            qnaDAO.createQna(qna);

        EcQna foundQna = qnaDAO.findById(qna.getQnaId());
        assertEquals(title, foundQna.getTitle());
        assertEquals(content, foundQna.getContent());
        assertEquals(partNo, foundQna.getPartNo());
        assertEquals(regId, foundQna.getRegId());
        assertEquals(seriesCode, foundQna.getSeriesCode());
        assertEquals(contactId, foundQna.getContactId());

    }

}
