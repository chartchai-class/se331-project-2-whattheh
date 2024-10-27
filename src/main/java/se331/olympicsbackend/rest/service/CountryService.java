package se331.olympicsbackend.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.olympicsbackend.rest.entity.Country;


import java.util.List;

public interface CountryService {
    Integer getCountryCount();
    Page<Country> getCountries(Integer pageSize, Integer page);
    Page<Country> getCountries(String title, Pageable page);
    Country getCountryById(Long id);
    Country save(Country country);
    List<Country> getAllCountries();
}
