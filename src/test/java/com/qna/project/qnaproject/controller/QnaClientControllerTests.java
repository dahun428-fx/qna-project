package com.qna.project.qnaproject.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.qna.project.qnaproject.dto.ec_qna.CreateQnaDTO;
import com.qna.project.qnaproject.dto.ec_qna.ReadQnaDTO;
import com.qna.project.qnaproject.service.QnaClientService;
import com.qna.project.qnaproject.utils.ModelUtil;
import com.qna.project.qnaproject.utils.MultiValueMapConverter;

@RunWith(SpringRunner.class)
// @TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest
@AutoConfigureMockMvc
// @WebMvcTest(QnaClientControllerTests.class)
public class QnaClientControllerTests {

    @Autowired
    private MockMvc mockMvc;

    // @Autowired
    @MockBean
    private QnaClientService clientService;

    @Test
    @DisplayName("Qna Create Test")
    public void createNewQnaTest() throws Exception {
        String url = "/q1/c1/api/qna/create";

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
        //request body dto type 과 response body dto type 이 다를때, 일치 여부 ( eqauls ) 명시 필요 
        when(clientService.createQna(dto)).thenReturn(ModelUtil.map(dto, ReadQnaDTO.class));

        
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                            .post(url)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(ModelUtil.toJSON(dto)))
                            .andDo(MockMvcResultHandlers.print())
                            .andExpect(MockMvcResultMatchers.status().isCreated())
                            .andReturn();
        ReadQnaDTO responseReadDto = new ObjectMapper().readValue(result.getResponse().getContentAsString(), ReadQnaDTO.class);
        
        BDDMockito.verify(clientService).createQna(dto);
        assertEquals(title, responseReadDto.getTitle());
        assertEquals(content, responseReadDto.getContent());
        assertEquals(partNo, responseReadDto.getPartNo());
        assertEquals(regId, responseReadDto.getRegId());
        assertEquals(seriesCode, responseReadDto.getSeriesCode());
        assertEquals(contactId, responseReadDto.getContactId());

    }

}
