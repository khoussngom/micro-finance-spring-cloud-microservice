package com.khouss.compte.controllers;

import com.khouss.compte.dtos.CreateCompte;
import com.khouss.compte.dtos.GetCompte;
import com.khouss.compte.services.ComptesServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comptes")
@RequiredArgsConstructor
public class CompteControllers {

    private final ComptesServices comptesServices;

    @GetMapping
    public List<GetCompte> getAllComptes() {
        return comptesServices.getAllComptes();
    }

    @PostMapping
    public GetCompte createCompte(@Valid @RequestBody CreateCompte compte) {
        return comptesServices.createCompte(compte);
    }

    @GetMapping("/exists/{numeroTelephone}")
    public boolean compteExists(@PathVariable String numeroTelephone) {
        return comptesServices.compteExistsByNumeroTelephone(numeroTelephone);
    }
}
