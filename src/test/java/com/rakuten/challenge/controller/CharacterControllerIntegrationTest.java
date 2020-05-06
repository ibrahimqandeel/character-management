package com.rakuten.challenge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.challenge.controller.CharacterController;
import com.rakuten.challenge.dto.CharacterDto;
import com.rakuten.challenge.exception.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.springframework.boot.actuate.autoconfigure.cloudfoundry.SecurityResponse.success;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
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

    @Before
    public void setup() {
        characterDtoRequest = new CharacterDto("My Character", 30, "dwarf", "barbarian");
        try {
            requestJson = new ObjectMapper().writeValueAsString(characterDtoRequest);
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testCreateCharacter() {
        CharacterDto responseDto = null;
        MvcResult mvcResponse = null;
        try {
            mvcResponse = mockMvc.perform(MockMvcRequestBuilders
                    .post("/character-management/characters")
                    .content(requestJson)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated()).andReturn();

            responseDto = new ObjectMapper().readValue(mvcResponse.getResponse().getContentAsString(), CharacterDto.class);
        } catch (Exception e) {
            fail();
        }

        assertNotNull(mvcResponse);
        assertNotNull(mvcResponse.getResponse());
        assertNotNull(responseDto);
        assertEquals(characterDtoRequest.getName(), responseDto.getName());
        assertEquals(characterDtoRequest.getAge(), responseDto.getAge());
        assertEquals(characterDtoRequest.getRaceIndex(), responseDto.getRaceIndex());
        assertEquals(characterDtoRequest.getClassIndex(), responseDto.getClassIndex());
    }

    @Test
    public void testViewCharacterInfoNotFound() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.
                    get("/character-management/characters/{name}", "name")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        } catch (ResourceNotFoundException e) {
            success();
        } catch (Exception e) {
            fail();
        }
    }
}
