package com.rakuten.challenge.serviceImpl;

import com.rakuten.challenge.client.RaceAPIClient;
import com.rakuten.challenge.dto.AllRacesDto;
import com.rakuten.challenge.dto.RaceDto;
import com.rakuten.challenge.exception.BusinessException;
import com.rakuten.challenge.exception.ErrorMessageCode;
import com.rakuten.challenge.service.RaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class RaceServiceImpl implements RaceService {
    private RaceAPIClient raceAPIClient;

    public RaceServiceImpl(RaceAPIClient raceAPIClient) {
        this.raceAPIClient = raceAPIClient;
    }

    @Override
    @Cacheable(value = "races")
    public Optional<AllRacesDto> getRaces() throws BusinessException {
        Optional<AllRacesDto> allRacesDto = raceAPIClient.getRaces();
        if (!allRacesDto.isPresent()) {
            throw new BusinessException(ErrorMessageCode.RESOURCE_NOT_FOUND_ERROR);
        }
        return allRacesDto;
    }

    @Override
    @Cacheable(value = "races", key = "#index")
    public Optional<RaceDto> getRaceInfo(String index) throws BusinessException {
        Optional<RaceDto> raceDto = raceAPIClient.getRaceInfo(index);
        if (!raceDto.isPresent()) {
            throw new BusinessException(ErrorMessageCode.RESOURCE_NOT_FOUND_ERROR, new String[]{index});
        }
        return raceDto;
    }
}
