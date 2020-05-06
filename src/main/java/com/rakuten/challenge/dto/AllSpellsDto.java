package com.rakuten.challenge.dto;

import lombok.Data;

import java.util.List;

@Data
public class AllSpellsDto {

    private int count;
    private List<SpellDto> results;
}