package com.h2sm.testtasktelda.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegionDTO {
    private long id;
    private String regionName;
    private String regionShortName;
}