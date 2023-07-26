package com.h2sm.testtasktelda;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.h2sm.testtasktelda.controllers.RegionsController;
import com.h2sm.testtasktelda.dtos.NewRegionDTO;
import com.h2sm.testtasktelda.dtos.Region;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    @SneakyThrows
    void getRegionsList() {
        var firstRegion = new Region(1, "Test", "tewst");
        var secondRegion = new Region(2, "Tes2", "tewst2");
        var expectedJSON = "[{\"regionId\":1,\"regionName\":\"Test\",\"regionShortName\":\"tewst\"},{\"regionId\":2,\"regionName\":\"Tes2\",\"regionShortName\":\"tewst2\"}]";

        when(service.getRegionList()).thenReturn(List.of(
                firstRegion, secondRegion
        ));

        mockMvc.perform(get("/api/regions/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJSON));
    }

    @Test
    @SneakyThrows
    void tryGetRegionInfo() {
        when(service.getRegion(anyLong())).thenReturn(new Region(1, "REG_NAME", "SHORT_NAME"));

        mockMvc.perform(get("/api/regions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @SneakyThrows
    void tryGetNonExistentRegionInfo() {
        when(service.getRegion(anyLong())).thenThrow(new IllegalArgumentException());

        mockMvc.perform(get("/api/regions/666")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
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
    @SneakyThrows
    void updateRegionInfo() {
        ObjectMapper mapper = new ObjectMapper();
        var updatedRegionAsJSON = mapper.writeValueAsString(NewRegionDTO.builder()
                .regionName("SAN-FRANCISCO")
                .regionShortName("DISCO")
                .build());

        mockMvc.perform(put("/api/regions/1")
                        .content(updatedRegionAsJSON)
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    @SneakyThrows
    void deleteRegionInfo() {
        mockMvc.perform(delete("/api/regions/1")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
}