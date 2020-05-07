package com.rakuten.challenge.controller;

import com.rakuten.challenge.dto.CharacterDto;
import com.rakuten.challenge.exception.BusinessException;
import com.rakuten.challenge.service.CharacterService;
import com.rakuten.challenge.service.ClassService;
import com.rakuten.challenge.service.RaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/character-management/characters")
@Api(value = "Characters Endpoints")
public class CharacterController {
    private final CharacterService characterService;
    private final RaceService raceService;
    private final ClassService classService;

    public CharacterController(CharacterService characterService, RaceService raceService, ClassService classService) {
        this.characterService = characterService;
        this.raceService = raceService;
        this.classService = classService;
    }

    @ApiOperation(value = "Create New Character")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Character Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 409, message = "Character Name Already Exist")
    })
    @PostMapping
    public ResponseEntity<CharacterDto> createCharacter(@Valid @RequestBody CharacterDto characterDto) throws BusinessException {
        String characterName = characterDto.getName();
        characterDto = characterService.createCharacter(characterDto);
        return new ResponseEntity<>(characterDto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get Character Info By Name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Character Found"),
            @ApiResponse(code = 404, message = "Character Not Found")
    })
    @GetMapping(path = "/{name}")
    public ResponseEntity<CharacterDto> viewCharacterInfo(@PathVariable String name) throws BusinessException {
        Optional<CharacterDto> characterDto = characterService.getByName(name);
        return new ResponseEntity<>(characterDto.get(), HttpStatus.OK);
    }
}
