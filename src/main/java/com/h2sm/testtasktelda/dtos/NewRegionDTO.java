package com.h2sm.testtasktelda.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewRegionDTO {
    private String regionName;
    private String regionShortName;
}
