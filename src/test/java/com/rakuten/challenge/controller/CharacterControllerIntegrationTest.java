package com.rakuten.challenge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.challenge.dto.CharacterDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CharacterControllerIntegrationTest {

    @Autowired
    private CharacterController characterController;

    @Autowired
    private MockMvc mockMvc;

    private CharacterDto characterDtoRequest;

    private String requestJson;

    @BeforeEach
    public void setup() throws JsonProcessingException {
        characterDtoRequest = new CharacterDto("My Character", 30, "dwarf", "barbarian");
        requestJson = new ObjectMapper().writeValueAsString(characterDtoRequest);
    }

    @Test
    public void testCreateCharacter() throws Exception {
        MvcResult mvcResponse = mockMvc.perform(MockMvcRequestBuilders
                .post("/character-management/characters")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated()).andReturn();

        CharacterDto responseDto = new ObjectMapper().readValue(mvcResponse.getResponse().getContentAsString(), CharacterDto.class);

        assertNotNull(mvcResponse);
        assertNotNull(mvcResponse.getResponse());
        assertNotNull(responseDto);
        assertEquals(characterDtoRequest.getName(), responseDto.getName());
        assertEquals(characterDtoRequest.getAge(), responseDto.getAge());
        assertEquals(characterDtoRequest.getRaceIndex(), responseDto.getRaceIndex());
        assertEquals(characterDtoRequest.getClassIndex(), responseDto.getClassIndex());
    }

    @Test
    public void testViewCharacterInfoNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                get("/character-management/characters/{name}", "name")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
