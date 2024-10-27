package se331.olympicsbackend.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.entity.CountryDTO;
import se331.olympicsbackend.rest.repository.CountryRepository;
import se331.olympicsbackend.rest.service.CountryService;
import se331.olympicsbackend.rest.util.LabMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CountryController {
    final CountryService countryService;

    @GetMapping("/home")
    public ResponseEntity<List<CountryDTO>> getCountryLists(
            @RequestParam(value = "_limit", required = false, defaultValue = "10") Integer perPage,
            @RequestParam(value = "_page", required = false, defaultValue = "1") Integer page
    ) {

        perPage=perPage==null?3:perPage;
        page=page==null?1:page;
        Page<Country> pageOutput;

        pageOutput=countryService.getCountries(perPage,page);

        //List<Country> countries = countryService.getAllCountries();  // Fetch countries

        // Map countries to CountryDTOs, including nested sports
        List<CountryDTO> countryDTOs = pageOutput.stream()
                .map(country -> {
                    CountryDTO dto = LabMapper.INSTANCE.getCountryDto(country);
                    dto.setSports(LabMapper.INSTANCE.toSportDTOs(country.getSports()));  // Set sports
                    return dto;
                }).collect(Collectors.toList());

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(countryDTOs.size()));

        return new ResponseEntity<>(countryDTOs, responseHeader, HttpStatus.OK);
    }

    @GetMapping("/countrydetails/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable Long id) {
        Optional<Country> country = Optional.ofNullable(countryService.getCountryById(id));
        return country.map(value ->
                        new ResponseEntity<>(LabMapper.INSTANCE.getCountryDto(value), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/countries")
    public ResponseEntity<?> addCountry(@RequestBody Country country){
        Country output=countryService.save(country);
        return ResponseEntity.ok(LabMapper.INSTANCE.getCountryDto(output));}
}
