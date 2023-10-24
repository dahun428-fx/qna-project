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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qna.project.qnaproject.dto.ec_qna.CreateQnaDTO;
import com.qna.project.qnaproject.dto.ec_qna.ReadQnaDTO;
import com.qna.project.qnaproject.service.QnaClientService;
import com.qna.project.qnaproject.utils.ModelUtil;

@RunWith(SpringRunner.class)
// @TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest
@AutoConfigureMockMvc
// @WebMvcTest(QnaClientControllerTests.class)
public class QnaClientControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QnaClientService clientService;

    @Value("classpath:test-dummy-data/create-qna-data.json")
    private Resource CREATE_QNA_JSON;

    private CreateQnaDTO createQnaDTO = null;

    @Before
    public void setUp() {

        ObjectMapper mapper = new ObjectMapper();

        try {
            
            Map<String, String> map = mapper.readValue(CREATE_QNA_JSON.getFile(), Map.class);
            createQnaDTO = ModelUtil.map(map, CreateQnaDTO.class);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Test
    @DisplayName("Qna Create Test")
    public void createNewQnaTest() throws Exception {
        String url = "/q1/c1/api/qna/create";

        CreateQnaDTO dto 
            = createQnaDTO;
        //request body dto type 과 response body dto type 이 다를때, 일치 여부 ( eqauls ) 명시 필요 
        when(clientService.createQna(dto)).thenReturn(ModelUtil.map(dto, ReadQnaDTO.class));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                            .post(url)
                            .characterEncoding("UTF-8")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(ModelUtil.toJSON(dto)))
                            .andDo(MockMvcResultHandlers.print())
                            .andExpect(MockMvcResultMatchers.status().isCreated())
                            .andReturn();
        result.getResponse().setCharacterEncoding("UTF-8");
        ReadQnaDTO responseReadDto = new ObjectMapper().readValue(result.getResponse().getContentAsString(), ReadQnaDTO.class);
        BDDMockito.verify(clientService).createQna(dto);

        assertEquals(dto.getTitle(), responseReadDto.getTitle());
        assertEquals(dto.getContent(), responseReadDto.getContent());
        assertEquals(dto.getPartNo(), responseReadDto.getPartNo());
        assertEquals(dto.getRegId(), responseReadDto.getRegId());
        assertEquals(dto.getSeriesCode(), responseReadDto.getSeriesCode());
        assertEquals(dto.getSeriesName(), responseReadDto.getSeriesName());
        assertEquals(dto.getBrandCode(), responseReadDto.getBrandCode());
        assertEquals(dto.getBrandName(), responseReadDto.getBrandName());
        assertEquals(dto.getContactId(), responseReadDto.getContactId());

    }

}
