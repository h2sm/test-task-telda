package com.h2sm.testtasktelda.services;

import com.h2sm.testtasktelda.dtos.NewRegionDTO;
import com.h2sm.testtasktelda.dtos.Region;
import com.h2sm.testtasktelda.repositories.RegionsRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class RegionsService {
    private final RegionsRepository regionsRepository;
    public void addRegion(NewRegionDTO newRegion) {
        regionsRepository.insert(newRegion);
    }

    @SneakyThrows
    public Region getRegion(Long regionId) {
        var desiredRegion = regionsRepository.findRegionById(regionId);
        if (desiredRegion == null) throw new NotFoundException("Entity not found");
        return regionsRepository.findRegionById(regionId);
    }

    public List<Region> getRegionList() {
        return regionsRepository.findAllRegions();
    }

    public void updateRegion(Long regionId, NewRegionDTO updatedRegion) {
        int rowsAffected = regionsRepository.updateRegionInfo(regionId, updatedRegion);
        if (rowsAffected == 0) throw new IllegalArgumentException();

    }

    public void deleteRegion(Long regionId) {
        int rowsAffected = regionsRepository.deleteRegionInfo(regionId);
        if (rowsAffected == 0) throw new IllegalArgumentException();
    }
}
