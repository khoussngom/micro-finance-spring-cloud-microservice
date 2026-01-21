package com.khouss.transfert.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "compte")
public interface CompteClient {

    @GetMapping("/comptes/exists/{numeroTelephone}")
    boolean compteExists(@PathVariable String numeroTelephone);
}