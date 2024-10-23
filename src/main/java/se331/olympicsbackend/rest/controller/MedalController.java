package se331.olympicsbackend.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import se331.olympicsbackend.rest.entity.Medal;
import se331.olympicsbackend.rest.service.MedalService;

import java.util.List;

public class MedalController {
    MedalService medalService;

    @GetMapping("/fetchMedals")
    public String fetchAndStoreMedals() {
        medalService.fetchAndStoreMedals();
        return "Medals fetched and stored successfully!";
    }

    @GetMapping
    public List<Medal> getAllMedals() {
        return medalService.getAllMedals();
    }
}
