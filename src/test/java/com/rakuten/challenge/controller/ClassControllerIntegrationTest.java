package com.rakuten.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.challenge.dto.AllClassesDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClassControllerIntegrationTest {

    @Autowired
    private ClassController classController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetClasses() throws Exception {

        MvcResult mvcResponse = mockMvc.perform(MockMvcRequestBuilders
                .get("/character-management/classes")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        AllClassesDto responseDto = new ObjectMapper().readValue(mvcResponse.getResponse().getContentAsString(), AllClassesDto.class);

        assertNotNull(responseDto);
        assertTrue(responseDto.getCount() >= 0);
        assertNotNull(responseDto.getResults());
    }
}
