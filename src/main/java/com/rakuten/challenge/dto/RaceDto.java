package com.rakuten.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RaceDto {

    private String index;
    private String name;
    private int speed;
    private String url;

//    public RaceDto() {
//    }
//
//    public RaceDto(String index, String name, int speed, String url) {
//        this.index = index;
//        this.name = name;
//        this.speed = speed;
//        this.url = url;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceDto raceDto = (RaceDto) o;
        return Objects.equals(index, raceDto.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
