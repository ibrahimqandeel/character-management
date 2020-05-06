package com.rakuten.challenge.client;


import com.rakuten.challenge.dto.AllClassesDto;
import com.rakuten.challenge.dto.AllSpellsDto;
import com.rakuten.challenge.dto.ClassDto;
import com.rakuten.challenge.dto.StartingEquipmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(name = "${dnd.classes.api.name}", url = "${dnd.classes.api.uri}")
public interface ClassAPIClient {

    @RequestMapping(method = RequestMethod.GET)
    Optional<AllClassesDto> getClasses();

    @RequestMapping(method = RequestMethod.GET, value = "/{index}")
    Optional<ClassDto> getClassInfo(@PathVariable String index);

    @RequestMapping(method = RequestMethod.GET, value = "/{index}/starting-equipment")
    Optional<StartingEquipmentDto> getClassStartingEquipment(@PathVariable String index);

    @RequestMapping(method = RequestMethod.GET, value = "/{index}/spells")
    Optional<AllSpellsDto> getClassStartingSpells(@PathVariable String index);
}