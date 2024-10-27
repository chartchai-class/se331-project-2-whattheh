package se331.olympicsbackend.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.olympicsbackend.rest.entity.Country;

public interface CountryDao {
    Integer getCountrySize();
    Country getCountry(Long id);
    // to save data from api post method
    Country save(Country country);

    Page<Country> getCountries(String name, Pageable page);
    Page<Country> getCountries(Integer pageSize, Integer page);
}
