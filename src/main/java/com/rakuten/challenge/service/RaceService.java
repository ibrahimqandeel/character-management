package com.rakuten.challenge.service;

import com.rakuten.challenge.dto.AllRacesDto;
import com.rakuten.challenge.dto.RaceDto;
import com.rakuten.challenge.exception.BusinessException;

import java.util.Optional;

public interface RaceService {
    Optional<AllRacesDto> getRaces() throws BusinessException;

    Optional<RaceDto> getRaceInfo(String index) throws BusinessException;
}
