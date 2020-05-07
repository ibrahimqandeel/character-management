package com.rakuten.challenge.controller;

import com.rakuten.challenge.dto.AllClassesDto;
import com.rakuten.challenge.exception.BusinessException;
import com.rakuten.challenge.service.ClassService;
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
@RequestMapping("/character-management/classes")
@Api(value = "Classes Endpoints")
public class ClassController {
    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @ApiOperation(value = "Get All Classes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Classes Found"),
            @ApiResponse(code = 404, message = "Classes Not Found")
    })
    @GetMapping
    public ResponseEntity<AllClassesDto> getClasses() throws BusinessException {
        Optional<AllClassesDto> allClassesDto = classService.getClasses();
        return new ResponseEntity<>(allClassesDto.get(), HttpStatus.OK);
    }

}
