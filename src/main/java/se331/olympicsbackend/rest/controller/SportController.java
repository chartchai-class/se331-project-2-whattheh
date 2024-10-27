package se331.olympicsbackend.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se331.olympicsbackend.rest.entity.Sport;
import se331.olympicsbackend.rest.repository.SportRepository;

@RestController
@RequiredArgsConstructor
public class SportController {

    private final SportRepository sportRepository;

    @PutMapping("/sports/{id}")
    public ResponseEntity<Sport> updateSport(@PathVariable Integer id, @RequestBody Sport updatedSport) {
        return sportRepository.findById(id)
                .map(sport -> {
                    sport.setSportName(updatedSport.getSportName());
                    sport.setGold(updatedSport.getGold());
                    sport.setSilver(updatedSport.getSilver());
                    sport.setBronze(updatedSport.getBronze());
                    sportRepository.save(sport);
                    return ResponseEntity.ok(sport);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

