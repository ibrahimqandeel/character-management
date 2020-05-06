package com.rakuten.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.challenge.controller.RaceController;
import com.rakuten.challenge.dto.AllClassesDto;
import com.rakuten.challenge.dto.AllRacesDto;
import com.rakuten.challenge.service.RaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class RaceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RaceController raceController;

    @Autowired
    private RaceService raceService;

    @Test
    public void testGetRaces() {

        AllRacesDto responseDto = null;
        MvcResult mvcResponse = null;
        try {
            mvcResponse = mockMvc.perform(MockMvcRequestBuilders
                    .get("/character-management/races")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk()).andReturn();

            responseDto = new ObjectMapper().readValue(mvcResponse.getResponse().getContentAsString(), AllRacesDto.class);
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
