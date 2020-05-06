package com.rakuten.challenge.service;

import com.rakuten.challenge.dto.CharacterDto;
import com.rakuten.challenge.entity.CharacterEntity;
import com.rakuten.challenge.exception.*;

import java.util.Optional;

public interface CharacterService {

    CharacterDto createCharacter(CharacterDto newRec) throws ResourceDuplicationException, BadRequestException, InternalServerException;

    Optional<CharacterDto> getByName(String name) throws ResourceNotFoundException, InternalServerException;
}
