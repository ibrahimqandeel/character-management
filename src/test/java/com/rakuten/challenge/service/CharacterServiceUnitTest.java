package com.rakuten.challenge.service;

import com.rakuten.challenge.dto.*;
import com.rakuten.challenge.entity.CharacterEntity;
import com.rakuten.challenge.exception.BusinessException;
import com.rakuten.challenge.repo.CharacterRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CharacterServiceUnitTest {
    @MockBean
    private CharacterRepo characterRepo;
    @MockBean
    private ClassService classService;
    @MockBean
    private RaceService raceService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CharacterService characterService;

    private CharacterEntity expectedCharacterEntity;
    private CharacterDto expectedCharacterDto;
    private RaceDto expectedRaceDto;
    private ClassDto expectedClassDto;
    private StartingEquipmentDto expectedStartingEquipmentDto = new StartingEquipmentDto();
    private AllSpellsDto expectedAllSpellsDto = new AllSpellsDto();

    @BeforeEach
    public void setup() throws BusinessException {
        MockitoAnnotations.initMocks(this);
        expectedCharacterEntity = new CharacterEntity("TestName", 30, "RaceIndex", "ClassIndex");
        expectedCharacterDto = new CharacterDto("TestName", 30, "RaceIndex", "ClassIndex");
        expectedRaceDto = new RaceDto("RaceIndex", "RaceName", "");
        expectedClassDto = new ClassDto("ClassIndex", "ClassName", "");

        when(characterRepo.existsByName(any(String.class))).thenReturn(false);
        when(characterRepo.save(any(CharacterEntity.class))).thenReturn(expectedCharacterEntity);
        when(characterRepo.findByName(any(String.class))).thenReturn(Optional.ofNullable(expectedCharacterEntity));
        when(raceService.getRaceInfo(any(String.class))).thenReturn(Optional.ofNullable(expectedRaceDto));
        when(classService.getClassInfo(any(String.class))).thenReturn(Optional.ofNullable(expectedClassDto));
        when(classService.getClassStartingEquipment(any(String.class))).thenReturn(Optional.ofNullable(expectedStartingEquipmentDto));
        when(classService.getClassSpells(any(String.class))).thenReturn(Optional.ofNullable(expectedAllSpellsDto));
    }

    @DisplayName("Test Create Character")
    @Test
    public void testCreateCharacter() throws BusinessException {
        CharacterDto newCharacterDto = characterService.createCharacter(new CharacterDto());
        assertNotNull(newCharacterDto);
        assertEquals(expectedCharacterDto.getName(), newCharacterDto.getName());
        assertEquals(expectedCharacterDto.getAge(), newCharacterDto.getAge());
        assertEquals(expectedCharacterDto.getRaceIndex(), newCharacterDto.getRaceIndex());
        assertEquals(expectedCharacterDto.getClassIndex(), newCharacterDto.getClassIndex());

    }

    @DisplayName("Test Character By Name")
    @Test
    public void testGetByName() throws BusinessException {
        Optional<CharacterDto> newCharacterDto = characterService.getByName("TestName");
        assertNotNull(newCharacterDto);
        assertNotNull(newCharacterDto.get());
        assertNotNull(newCharacterDto.get().getRaceName());
        assertNotNull(newCharacterDto.get().getClassName());
        assertNotNull(newCharacterDto.get().getStartingEquipment());
        assertNotNull(newCharacterDto.get().getSpells());
    }
}
