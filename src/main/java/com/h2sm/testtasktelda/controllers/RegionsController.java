package com.h2sm.testtasktelda.controllers;
import com.h2sm.testtasktelda.dtos.NewRegionDTO;
import com.h2sm.testtasktelda.dtos.RegionDTO;
import com.h2sm.testtasktelda.services.RegionsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/regions")
public class RegionsController {
    private RegionsService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<RegionDTO> getRegionsList() {
        return service.getRegionList();
    }

    @RequestMapping(value = "/{regionId}", method = RequestMethod.GET)
    public RegionDTO getRegionInfo(@PathVariable Long regionId) {
        return service.getRegion(regionId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public RegionDTO addRegionInfo(@RequestBody NewRegionDTO newRegion) {
        return service.addRegion(newRegion);
    }

    @RequestMapping(value = "/{regionId}", method = RequestMethod.PUT)
    public RegionDTO updateRegionInfo(@PathVariable Long regionId, @RequestBody NewRegionDTO updatedRegion) {
        return service.updateRegion(regionId, updatedRegion);
    }

    @RequestMapping(value = "/{regionId}", method = RequestMethod.DELETE)
    public void deleteRegionInfo(@PathVariable Long regionId) {
        service.deleteRegion(regionId);
    }

}
