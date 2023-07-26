package com.h2sm.testtasktelda;

import com.h2sm.testtasktelda.dtos.NewRegionDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.anyLong;

import com.h2sm.testtasktelda.repositories.RegionsRepository;
import com.h2sm.testtasktelda.services.RegionsService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:testDataSQL.sql"}),
})
public class TestRegionsService {
    @Autowired
    private RegionsService service;

    @Autowired
    private RegionsRepository regionsRepository;


    @Test
    @SneakyThrows
    public void addNewRegionAndCheckLength() {
        var newRegion = NewRegionDTO.builder()
                .regionName("SAN-FRANCISCO")
                .regionShortName("DISCO")
                .build();

        service.addRegion(newRegion);

        assertEquals(7, service.getRegionList().size());
    }

    @Test
    public void tryDeleteExistingRegionAndCheck() {
        service.deleteRegion(1L);
        assertEquals(5, service.getRegionList().size());
    }

    @Test
    public void tryDeleteNonExistentRegionAndCheck() {
        assertThrowsExactly(IllegalArgumentException.class, () -> service.deleteRegion(anyLong()));
    }

    @Test
    public void tryAddTwoSameRegions() {
        var duplicatedRegion = NewRegionDTO.builder()
                .regionName("SAN-FRANCISCO")
                .regionShortName("DISCO")
                .build();

        service.addRegion(duplicatedRegion);

        assertThrowsExactly(DuplicateKeyException.class, () -> service.addRegion(duplicatedRegion));
    }

    @Test
    public void tryUpdateRegionAndCheck() {
    }

}
