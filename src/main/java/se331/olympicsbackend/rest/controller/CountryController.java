package se331.olympicsbackend.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.entity.CountryDTO;
import se331.olympicsbackend.rest.repository.CountryRepository;
import se331.olympicsbackend.rest.service.CountryService;
import se331.olympicsbackend.rest.util.LabMapper;

import java.util.List;
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
        List<Country> countries = countryService.getAllCountries();  // Get list of countries

        // Map each Country entity to CountryDTO using LabMapper
        List<CountryDTO> countryDTOs = countries.stream()
                .map(LabMapper.INSTANCE::getCountryDto)  // Convert Country to CountryDTO
                .collect(Collectors.toList());  // Collect into a List

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(countryDTOs.size()));

        return new ResponseEntity<>(countryDTOs, responseHeader, HttpStatus.OK);
    }
}
