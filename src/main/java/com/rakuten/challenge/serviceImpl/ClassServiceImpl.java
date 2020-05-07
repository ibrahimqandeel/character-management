package com.rakuten.challenge.serviceImpl;

import com.rakuten.challenge.client.ClassAPIClient;
import com.rakuten.challenge.dto.AllClassesDto;
import com.rakuten.challenge.dto.AllSpellsDto;
import com.rakuten.challenge.dto.ClassDto;
import com.rakuten.challenge.dto.StartingEquipmentDto;
import com.rakuten.challenge.exception.BusinessException;
import com.rakuten.challenge.exception.ErrorMessageCode;
import com.rakuten.challenge.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ClassServiceImpl implements ClassService {
    private ClassAPIClient classAPIClient;

    public ClassServiceImpl(ClassAPIClient classAPIClient) {
        this.classAPIClient = classAPIClient;
    }

    @Override
    @Cacheable(value = "classes")
    public Optional<AllClassesDto> getClasses() throws BusinessException {
        Optional<AllClassesDto> allClassesDto = classAPIClient.getClasses();
        if (!allClassesDto.isPresent()) {
            throw new BusinessException(ErrorMessageCode.RESOURCE_NOT_FOUND_ERROR);
        }
        return allClassesDto;
    }

    @Override
    @Cacheable(value = "classes", key = "#index")
    public Optional<ClassDto> getClassInfo(String index) throws BusinessException {
        Optional<ClassDto> classDto = classAPIClient.getClassInfo(index);
        if (!classDto.isPresent()) {
            throw new BusinessException(ErrorMessageCode.RESOURCE_NOT_FOUND_ERROR, new String[]{index});
        }
        return classDto;
    }

    @Override
    @Cacheable(value = "equipments", key = "#classIndex")
    public Optional<StartingEquipmentDto> getClassStartingEquipment(String classIndex) throws BusinessException {
        Optional<StartingEquipmentDto> startingEquipment = classAPIClient.getClassStartingEquipment(classIndex);
        if (!startingEquipment.isPresent()) {
            throw new BusinessException(ErrorMessageCode.RESOURCE_NOT_FOUND_ERROR, new String[]{classIndex});
        }
        return startingEquipment;
    }

    @Override
    @Cacheable(value = "spells", key = "#classIndex")
    public Optional<AllSpellsDto> getClassSpells(String classIndex) throws BusinessException {
        Optional<AllSpellsDto> allSpellsDto = classAPIClient.getClassStartingSpells(classIndex);
        if (!allSpellsDto.isPresent()) {
            throw new BusinessException(ErrorMessageCode.RESOURCE_NOT_FOUND_ERROR, new String[]{classIndex});
        }
        return allSpellsDto;
    }
}
