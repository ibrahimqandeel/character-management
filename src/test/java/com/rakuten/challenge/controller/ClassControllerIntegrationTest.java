package com.rakuten.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.challenge.dto.AllClassesDto;
import com.rakuten.challenge.dto.AllRacesDto;
import com.rakuten.challenge.exception.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClassControllerIntegrationTest {

    @Autowired
    private ClassController classController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetClasses() {

        AllClassesDto responseDto = null;
        MvcResult mvcResponse = null;
        try {
            mvcResponse = mockMvc.perform(MockMvcRequestBuilders
                    .get("/character-management/classes")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk()).andReturn();

            responseDto = new ObjectMapper().readValue(mvcResponse.getResponse().getContentAsString(), AllClassesDto.class);
        } catch (Exception e) {
            fail();
        }

        assertNotNull(mvcResponse);
        assertNotNull(mvcResponse.getResponse());
        assertThat(responseDto.getCount(), greaterThanOrEqualTo(0));
        assertNotNull(responseDto.getResults());
        assertThat(responseDto.getResults().size(), greaterThanOrEqualTo(0));
    }
}
