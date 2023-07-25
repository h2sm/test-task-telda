package com.h2sm.testtasktelda.controllers;

import com.h2sm.testtasktelda.dtos.NewRegionDTO;
import com.h2sm.testtasktelda.dtos.Region;
import com.h2sm.testtasktelda.services.RegionsService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/regions")
public class RegionsController {
    private RegionsService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Region> getRegionsList() {
        return service.getRegionList();
    }

    @RequestMapping(value = "/{regionId}", method = RequestMethod.GET)
    public Region getRegionInfo(@PathVariable Long regionId) {
        return service.getRegion(regionId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addRegionInfo(@RequestBody NewRegionDTO newRegion) {
        service.addRegion(newRegion);
    }

    @RequestMapping(value = "/{regionId}", method = RequestMethod.PUT)
    public Region updateRegionInfo(@PathVariable Long regionId, @RequestBody NewRegionDTO updatedRegion) {
        return service.updateRegion(regionId, updatedRegion);
    }

    @RequestMapping(value = "/{regionId}", method = RequestMethod.DELETE)
    public void deleteRegionInfo(@PathVariable Long regionId) {
        service.deleteRegion(regionId);
    }

}
