package com.rakuten.challenge.client;


import com.rakuten.challenge.dto.AllRacesDto;
import com.rakuten.challenge.dto.RaceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(name = "${dnd.races.api.name}", url = "${dnd.races.api.uri}")
public interface RaceAPIClient {

    @RequestMapping(method = RequestMethod.GET)
    Optional<AllRacesDto> getRaces();

    @RequestMapping(method = RequestMethod.GET, value = "/{index}")
    Optional<RaceDto> getRaceInfo(@PathVariable String index);
}
