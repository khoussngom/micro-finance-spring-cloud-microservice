package com.khouss.daf.controllers;

import com.khouss.daf.dto.CitoyenDto;
import com.khouss.daf.services.CitoyenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/daf")
public class DafControllers {

    private final CitoyenService citoyenService;

    @GetMapping("/getAll")
    public List<CitoyenDto> getAll() {
        return citoyenService.findAllCitoyens();
    }
}
