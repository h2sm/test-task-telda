package com.h2sm.testtasktelda.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NewRegionDTO implements Serializable {
    private String regionName;
    private String regionShortName;
}
