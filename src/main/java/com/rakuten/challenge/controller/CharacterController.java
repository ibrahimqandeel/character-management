package com.rakuten.challenge.controller;

import com.rakuten.challenge.dto.CharacterDto;
import com.rakuten.challenge.exception.*;
import com.rakuten.challenge.service.CharacterService;
import com.rakuten.challenge.service.ClassService;
import com.rakuten.challenge.service.RaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/character-management/characters")
@Api(value = "Characters Endpoints")
public class CharacterController extends AppRestController {
    private CharacterService characterService;
    private RaceService raceService;
    private ClassService classService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @ApiOperation(value = "Create New Character")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Character Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 409, message = "Character Name Already Exist")
    })
    @PostMapping
    public ResponseEntity<CharacterDto> createCharacter(@Valid @RequestBody CharacterDto characterDto) throws ResourceDuplicationException, BadRequestException, InternalServerException {
        String characterName = characterDto.getName();
        try {
            characterDto = characterService.createCharacter(characterDto);
            return new ResponseEntity<CharacterDto>(characterDto, HttpStatus.CREATED);
        } catch (ResourceDuplicationException ex) {
            throw generateExceptionDetailsWithParam(ex, "error.character.name.exist", HttpStatus.CONFLICT.value(), new String[]{characterName});
        } catch (BadRequestException ex) {
            throw generateExceptionDetails(ex, "error.general.bad.request", HttpStatus.BAD_REQUEST.value());
        }
    }

    @ApiOperation(value = "Get Character Info By Name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Character Found"),
            @ApiResponse(code = 404, message = "Character Not Found")
    })
    @GetMapping(path = "/{name}")
    public ResponseEntity<CharacterDto> viewCharacterInfo(@PathVariable String name) throws ResourceNotFoundException, InternalServerException {
        try {
            Optional<CharacterDto> characterDto = characterService.getByName(name);
            return new ResponseEntity<CharacterDto>(characterDto.get(), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            throw generateExceptionDetailsWithParam(ex, "error.general.resource.notFound", HttpStatus.NOT_FOUND.value(), new String[]{name});
        }
    }
}
