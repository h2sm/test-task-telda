package com.h2sm.testtasktelda.repositories;

import com.h2sm.testtasktelda.dtos.NewRegionDTO;
import com.h2sm.testtasktelda.dtos.Region;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RegionsRepository {
    @Select("SELECT * FROM regions")
    List<Region> findAllRegions();

    @Select("SELECT * FROM regions WHERE id = #{regionId}")
    Region findRegionById(Long regionId);

    @Insert("INSERT INTO regions VALUES (#{regionName}, #{regionShortName})")
    Region addRegionInfo(NewRegionDTO dto);

    @Update("UPDATE regions SET region_name = #{regionName}, short_region_name = #{regionShortName} WHERE id = #{regionId}")
    Region updateRegionInfo(Long regionId, NewRegionDTO dto);
    @Delete("DELETE FROM regions WHERE id = #{regionId}")
    void deleteRegionInfo(Long regionId);
}
