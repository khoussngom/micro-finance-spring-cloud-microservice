package com.khouss.transfert.controllers;

import com.khouss.transfert.dtos.TransfertRequest;
import com.khouss.transfert.services.TransfertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transferts")
@RequiredArgsConstructor
public class TransfertController {

    private final TransfertService transferService;

    @PostMapping
    public ResponseEntity<String> transfer(@Valid @RequestBody TransfertRequest request) {

        return ResponseEntity.ok(
                transferService.startTransfer(request)
        );
    }
}
