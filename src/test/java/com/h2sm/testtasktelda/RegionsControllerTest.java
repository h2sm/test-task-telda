package com.h2sm.testtasktelda;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.h2sm.testtasktelda.controllers.RegionsController;
import com.h2sm.testtasktelda.dtos.NewRegionDTO;
import com.h2sm.testtasktelda.repositories.RegionsRepository;
import com.h2sm.testtasktelda.services.RegionsService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegionsController.class)
@ExtendWith(SpringExtension.class)
class RegionsControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    RegionsService service;

    @MockBean
    RegionsRepository regionsRepository;


    @Test
    void getRegionsList() {

    }

    @Test
    @SneakyThrows
    void tryGetRegionInfo() {
        //when()
        mockMvc.perform(get("/api/regions/1")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @SneakyThrows
    void tryAddRegionAndExpect200() {
        ObjectMapper mapper = new ObjectMapper();
        var newRegionAsJSON = mapper.writeValueAsString(NewRegionDTO.builder()
                .regionName("SAN-FRANCISCO")
                .regionShortName("DISCO")
                .build());


        mockMvc.perform(post("/api/regions")
                        .content(newRegionAsJSON)
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void updateRegionInfo() {
    }

    @Test
    void deleteRegionInfo() {
    }
}