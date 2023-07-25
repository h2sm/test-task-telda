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
        var region = regionsRepository.findRegionById(regionId);
        if (region == null) throw new NotFoundException("Entity not found");
        return regionsRepository.findRegionById(regionId);
    }

    public List<Region> getRegionList() {
        return regionsRepository.findAllRegions();
    }

    public Region updateRegion(Long regionId, NewRegionDTO updatedRegion) {
        return regionsRepository.updateRegionInfo(regionId, updatedRegion);
    }

    public void deleteRegion(Long regionId) {
        regionsRepository.deleteRegionInfo(regionId);
    }
}
