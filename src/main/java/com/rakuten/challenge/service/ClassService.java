package com.rakuten.challenge.service;

import com.rakuten.challenge.dto.AllClassesDto;
import com.rakuten.challenge.dto.AllSpellsDto;
import com.rakuten.challenge.dto.ClassDto;
import com.rakuten.challenge.dto.StartingEquipmentDto;
import com.rakuten.challenge.exception.ResourceNotFoundException;

import java.util.Optional;

public interface ClassService {
    Optional<AllClassesDto> getClasses() throws ResourceNotFoundException;

    Optional<ClassDto> getClassInfo(String classIndex) throws ResourceNotFoundException;

    Optional<StartingEquipmentDto> getClassStartingEquipment(String classIndex) throws ResourceNotFoundException;

    Optional<AllSpellsDto> getClassSpells(String classIndex) throws ResourceNotFoundException;

}
