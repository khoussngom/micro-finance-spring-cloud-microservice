package com.khouss.compte.openFeign;

import com.khouss.compte.dtos.CitoyenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="daf")
public interface DafClient {

    @GetMapping("/citoyens/{numCni}")
    CitoyenDto getCitoyenByNumCni(@PathVariable("numCni") Long numCni);
}