package com.h2sm.testtasktelda.controllers;

import com.h2sm.testtasktelda.dtos.NewRegionDTO;
import com.h2sm.testtasktelda.dtos.Region;
import com.h2sm.testtasktelda.services.RegionsService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/regions")
public class RegionsController {
    private RegionsService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @Cacheable("regionsList")
    public List<Region> getRegionsList() {
        return service.getRegionList();
    }

    @RequestMapping(value = "/{regionId}", method = RequestMethod.GET)
    @Cacheable(value = "getRegion", key = "#regionId")
    public Region getRegionInfo(@PathVariable Long regionId) {
        return service.getRegion(regionId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addRegionInfo(@RequestBody NewRegionDTO newRegion) {
        service.addRegion(newRegion);
    }

    @RequestMapping(value = "/{regionId}", method = RequestMethod.PUT)
    @Caching(evict = {
            @CacheEvict("getRegion"),
            @CacheEvict("regionsList")
    })
    public void updateRegionInfo(@PathVariable Long regionId, @RequestBody NewRegionDTO updatedRegion) {
        service.updateRegion(regionId, updatedRegion);
    }

    @RequestMapping(value = "/{regionId}", method = RequestMethod.DELETE)
    @Caching(evict = {
            @CacheEvict("getRegion"),
            @CacheEvict("regionsList")
    })
    public void deleteRegionInfo(@PathVariable Long regionId) {
        service.deleteRegion(regionId);
    }

}
