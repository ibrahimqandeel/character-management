package com.rakuten.challenge.dto;

import lombok.Data;

import java.util.List;

@Data
public class AllRacesDto {

    private int count;
    private List<RaceDto> results;
}
