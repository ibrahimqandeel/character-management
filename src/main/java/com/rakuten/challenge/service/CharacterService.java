package com.rakuten.challenge.service;

import com.rakuten.challenge.dto.CharacterDto;
import com.rakuten.challenge.exception.BusinessException;

import java.util.Optional;

public interface CharacterService {

    CharacterDto createCharacter(CharacterDto newRec) throws BusinessException;

    Optional<CharacterDto> getByName(String name) throws BusinessException;
}
