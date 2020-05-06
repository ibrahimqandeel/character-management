package com.rakuten.challenge.serviceImpl;

import com.rakuten.challenge.dto.*;
import com.rakuten.challenge.entity.CharacterEntity;
import com.rakuten.challenge.exception.BadRequestException;
import com.rakuten.challenge.exception.InternalServerException;
import com.rakuten.challenge.exception.ResourceDuplicationException;
import com.rakuten.challenge.exception.ResourceNotFoundException;
import com.rakuten.challenge.repo.CharacterRepo;
import com.rakuten.challenge.service.CharacterService;
import com.rakuten.challenge.service.ClassService;
import com.rakuten.challenge.service.RaceService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CharacterServiceImpl implements CharacterService {
    private CharacterRepo characterRepo;
    private ClassService classService;
    private RaceService raceService;
    private ModelMapper modelMapper;

    public CharacterServiceImpl(CharacterRepo characterRepo, ClassService classService, RaceService raceService, ModelMapper modelMapper) {
        this.characterRepo = characterRepo;
        this.classService = classService;
        this.raceService = raceService;
        this.modelMapper = modelMapper;
    }

    @Override
    public CharacterDto createCharacter(CharacterDto newCharacterDto) throws ResourceDuplicationException, BadRequestException, InternalServerException {
        boolean isCharacterNameExist = characterRepo.existsByName(newCharacterDto.getName());
        if (isCharacterNameExist) {
            throw new ResourceDuplicationException();
        }
        CharacterEntity newCharacterEntity;
        try {
            newCharacterEntity = modelMapper.map(newCharacterDto, CharacterEntity.class);
        } catch (MappingException ex) {
            log.error("Error: Mapping Exception. Unable to map From: {} object. To: {} object. source object: {} ",
                    "CharacterDto",
                    "CharacterEntity",
                    newCharacterDto);
            throw new BadRequestException();
        }

        newCharacterEntity = characterRepo.save(newCharacterEntity);
        try {
            newCharacterDto = modelMapper.map(newCharacterEntity, CharacterDto.class);
        } catch (MappingException ex) {
            log.error("Error: Mapping Exception. Unable to map From: {} object. To: {} object. source object: {} ",
                    "CharacterEntity",
                    "CharacterDto",
                    newCharacterEntity);
            throw new InternalServerException();
        }

        return newCharacterDto;
    }

    @Override
    public Optional<CharacterDto> getByName(String name) throws ResourceNotFoundException, InternalServerException {
        Optional<CharacterEntity> entityRec = characterRepo.findByName(name);
        if (!entityRec.isPresent()) {
            throw new ResourceNotFoundException();
        }
        try {
            CharacterDto characterDto = modelMapper.map(entityRec.get(), CharacterDto.class);

            //get race name
            Optional<RaceDto> raceDto = raceService.getRaceInfo(characterDto.getRaceIndex());
            characterDto.setRaceName(raceDto.get().getName());

            //get class name
            Optional<ClassDto> classDto = classService.getClassInfo(characterDto.getClassIndex());
            characterDto.setClassName(classDto.get().getName());

            //get starting equipments
            Optional<StartingEquipmentDto> startingEquipmentDto = classService.getClassStartingEquipment(characterDto.getClassIndex());
            characterDto.setStartingEquipment(startingEquipmentDto.get());

            //get spells
            Optional<AllSpellsDto> allSpellsDto = classService.getClassSpells(characterDto.getClassIndex());
            characterDto.setSpells(allSpellsDto.get());
            return Optional.of(characterDto);
        } catch (MappingException ex) {
            log.error("Error: Mapping Exception. Unable to map From: {} object. To: {} object. source object: {} ",
                    "CharacterEntity",
                    "CharacterDto",
                    entityRec.get());
            throw new InternalServerException();
        }
    }
}
