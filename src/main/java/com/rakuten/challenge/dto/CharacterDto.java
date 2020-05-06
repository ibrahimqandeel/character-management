package com.rakuten.challenge.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CharacterDto {

    @NotEmpty
    @Size(min = 1, max = 30)
    private String name;

    @NotNull
    @Min(1)
    private Integer age;

    @NotEmpty
    private String raceIndex;

    @NotEmpty
    private String classIndex;

    private String raceName;

    private String className;

    private StartingEquipmentDto startingEquipment;

    private AllSpellsDto spells;

    public CharacterDto() {
    }

    public CharacterDto(@NotEmpty String name, @NotNull Integer age, @NotEmpty String raceIndex, @NotEmpty String classIndex) {
        this.name = name;
        this.age = age;
        this.raceIndex = raceIndex;
        this.classIndex = classIndex;
    }
}
