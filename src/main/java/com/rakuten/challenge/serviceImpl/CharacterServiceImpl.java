package com.rakuten.challenge.serviceImpl;

import com.rakuten.challenge.dto.*;
import com.rakuten.challenge.entity.CharacterEntity;
import com.rakuten.challenge.exception.BusinessException;
import com.rakuten.challenge.exception.ErrorMessageCode;
import com.rakuten.challenge.repo.CharacterRepo;
import com.rakuten.challenge.service.CharacterService;
import com.rakuten.challenge.service.ClassService;
import com.rakuten.challenge.service.RaceService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepo characterRepo;
    private final ClassService classService;
    private final RaceService raceService;
    private final ModelMapper modelMapper;

    public CharacterServiceImpl(CharacterRepo characterRepo, ClassService classService, RaceService raceService, ModelMapper modelMapper) {
        this.characterRepo = characterRepo;
        this.classService = classService;
        this.raceService = raceService;
        this.modelMapper = modelMapper;
    }

    @Override
    public CharacterDto createCharacter(CharacterDto newCharacterDto) throws BusinessException {
        boolean isCharacterNameExist = characterRepo.existsByName(newCharacterDto.getName());
        if (isCharacterNameExist) {
            throw new BusinessException(ErrorMessageCode.CHARACTER_NAME_DUPLICATION, new String[]{newCharacterDto.getName()});
        }
        CharacterEntity newCharacterEntity;
        try {
            newCharacterEntity = modelMapper.map(newCharacterDto, CharacterEntity.class);
        } catch (MappingException ex) {
            log.error("Error: Mapping Exception. Unable to map From: {} object. To: {} object. source object: {} ",
                    "CharacterDto",
                    "CharacterEntity",
                    newCharacterDto);
            throw new BusinessException(ErrorMessageCode.BAD_REQUEST_ERROR);
        }

        newCharacterEntity = characterRepo.save(newCharacterEntity);
        try {
            newCharacterDto = modelMapper.map(newCharacterEntity, CharacterDto.class);
        } catch (MappingException ex) {
            log.error("Error: Mapping Exception. Unable to map From: {} object. To: {} object. source object: {} ",
                    "CharacterEntity",
                    "CharacterDto",
                    newCharacterEntity);
            throw new BusinessException(ErrorMessageCode.UNABLE_TO_BUILD_RESPONSE);
        }

        return newCharacterDto;
    }

    @Override
    public Optional<CharacterDto> getByName(String name) throws BusinessException {
        Optional<CharacterEntity> entityRec = characterRepo.findByName(name);
        if (!entityRec.isPresent()) {
            throw new BusinessException(ErrorMessageCode.CHARACTER_NOT_FOUND_ERROR, new String[]{name});
        }
        try {
            CharacterDto characterDto = modelMapper.map(entityRec.get(), CharacterDto.class);

            //get race name
            Optional<RaceDto> raceDto = raceService.getRaceInfo(characterDto.getRaceIndex());
            raceDto.ifPresent(dto -> characterDto.setRaceName(dto.getName()));

            //get class name
            Optional<ClassDto> classDto = classService.getClassInfo(characterDto.getClassIndex());
            classDto.ifPresent(dto -> characterDto.setClassName(dto.getName()));

            //get starting equipments
            Optional<StartingEquipmentDto> startingEquipmentDto = classService.getClassStartingEquipment(characterDto.getClassIndex());
            startingEquipmentDto.ifPresent(characterDto::setStartingEquipment);

            //get spells
            Optional<AllSpellsDto> allSpellsDto = classService.getClassSpells(characterDto.getClassIndex());
            allSpellsDto.ifPresent(characterDto::setSpells);
            return Optional.of(characterDto);
        } catch (MappingException ex) {
            log.error("Error: Mapping Exception. Unable to map From: {} object. To: {} object. source object: {} ", "CharacterEntity", "CharacterDto", entityRec.get());
            throw new BusinessException(ErrorMessageCode.UNABLE_TO_BUILD_RESPONSE);
        }
    }
}
