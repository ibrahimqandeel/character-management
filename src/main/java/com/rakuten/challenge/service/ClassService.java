package com.rakuten.challenge.service;

import com.rakuten.challenge.dto.AllClassesDto;
import com.rakuten.challenge.dto.AllSpellsDto;
import com.rakuten.challenge.dto.ClassDto;
import com.rakuten.challenge.dto.StartingEquipmentDto;
import com.rakuten.challenge.exception.BusinessException;

import java.util.Optional;

public interface ClassService {
    Optional<AllClassesDto> getClasses() throws BusinessException;

    Optional<ClassDto> getClassInfo(String classIndex) throws BusinessException;

    Optional<StartingEquipmentDto> getClassStartingEquipment(String classIndex) throws BusinessException;

    Optional<AllSpellsDto> getClassSpells(String classIndex) throws BusinessException;

}
