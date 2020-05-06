package com.rakuten.challenge.dto;

import lombok.Data;

import java.util.List;

@Data
public class AllClassesDto {

    private int count;
    private List<ClassDto> results;
}
