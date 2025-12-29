package com.khouss.daf.controllers;

import com.khouss.daf.dto.CitoyenDto;
import com.khouss.daf.services.CitoyenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/citoyens")
public class DafControllers {

    private final CitoyenService citoyenService;


    @GetMapping("/getAll")
    public List<CitoyenDto> getAll() {
        return citoyenService.findAllCitoyens();
    }


    @GetMapping("/{numCni}")
    public ResponseEntity<CitoyenDto> getCitoyenByNumCni(@PathVariable Long numCni) {
        CitoyenDto citoyen = citoyenService.findByNumCni(numCni);
        if (citoyen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(citoyen);
    }
}
