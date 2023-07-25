package com.h2sm.testtasktelda.repositories;

import com.h2sm.testtasktelda.dtos.NewRegionDTO;
import com.h2sm.testtasktelda.dtos.Region;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RegionsRepository {
    @Results({
            @Result(property = "regionId", column = "region_id"),
            @Result(property = "regionName", column = "region_name"),
            @Result(property = "regionShortName", column = "short_region_name")
    })
    @Select("SELECT * FROM regions")
    List<Region> findAllRegions();

    @Select("SELECT TOP 1 * FROM regions WHERE region_id = #{regionId}")
    @Results({
            @Result(property = "regionId", column = "region_id"),
            @Result(property = "regionName", column = "region_name"),
            @Result(property = "regionShortName", column = "short_region_name")
    })
    Region findRegionById(Long regionId);

    @Insert("INSERT INTO public.regions (region_name, short_region_name) VALUES (#{regionName}, #{regionShortName})")
    void insert(NewRegionDTO newRegion);

    @Update("UPDATE regions SET region_name = #{dto.regionName}, short_region_name = #{dto.regionShortName} WHERE region_id = #{regionId}")
    void updateRegionInfo(Long regionId, NewRegionDTO dto);
    @Delete("DELETE FROM regions WHERE region_id = #{regionId}")
    void deleteRegionInfo(Long regionId);
}
