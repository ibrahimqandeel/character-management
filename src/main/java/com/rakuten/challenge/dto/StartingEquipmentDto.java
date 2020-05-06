package com.rakuten.challenge.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class StartingEquipmentDto {

    @JsonAlias(value = "starting_equipment")
    private List<EquipmentDto> startingEquipmentDtos;
}
