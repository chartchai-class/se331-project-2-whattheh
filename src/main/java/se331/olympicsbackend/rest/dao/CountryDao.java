package se331.olympicsbackend.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.entity.CountryDTO;

import java.util.List;

public interface CountryDao {
    Integer getCountrySize();
    Country getCountry(Long id);
    Page<Country> getCountries(Integer pageSize, Integer page);
    Page<Country> getCountries(String name, Pageable page);
    Country save(Country country);
    //List<CountryDTO> getAllCountries();
    List<Country> getAllCountries() ;


}
