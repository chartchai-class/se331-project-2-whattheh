package se331.olympicsbackend.rest.dao;

import se331.olympicsbackend.rest.entity.Country;

public interface CountryDao {
    Integer getCountrySize();
    Country getCountry(Long id);
    // to save data from api post method
    Country save(Country country);
}
