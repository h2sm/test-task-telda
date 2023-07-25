package com.h2sm.testtasktelda.services;

import com.h2sm.testtasktelda.dtos.NewRegionDTO;
import com.h2sm.testtasktelda.dtos.Region;
import com.h2sm.testtasktelda.repositories.RegionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegionsService {
    private final RegionsRepository regionsRepository;
    public Region addRegion(NewRegionDTO newRegion) {
        return regionsRepository.addRegionInfo(newRegion);
    }

    public Region getRegion(Long regionId) {
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
