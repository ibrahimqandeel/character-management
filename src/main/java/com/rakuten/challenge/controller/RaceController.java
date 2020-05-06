package com.rakuten.challenge.controller;

import com.rakuten.challenge.dto.AllRacesDto;
import com.rakuten.challenge.exception.ResourceNotFoundException;
import com.rakuten.challenge.service.RaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/character-management/races")
@Api(value = "Races Endpoints")
public class RaceController extends AppRestController {
    private RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @ApiOperation(value = "Get All Races")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Races Found"),
            @ApiResponse(code = 404, message = "Races Not Found")
    })
    @GetMapping
    public ResponseEntity<AllRacesDto> getRaces() throws ResourceNotFoundException {
        try {
            Optional<AllRacesDto> allRacesDto = raceService.getRaces();
            return new ResponseEntity<>(allRacesDto.get(), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            throw generateExceptionDetails(ex, "error.general.resource.notFound", HttpStatus.NOT_FOUND.value());
        }

    }
}
