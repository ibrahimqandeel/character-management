package com.rakuten.challenge.service;

import com.rakuten.challenge.dto.AllRacesDto;
import com.rakuten.challenge.dto.RaceDto;
import com.rakuten.challenge.exception.BusinessException;
import com.rakuten.challenge.exception.ResourceNotFoundException;

import java.util.Optional;

public interface RaceService {
    Optional<AllRacesDto> getRaces() throws ResourceNotFoundException;

    Optional<RaceDto> getRaceInfo(String index) throws ResourceNotFoundException;
}
