package se331.olympicsbackend.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.olympicsbackend.rest.dao.CountryDao;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.entity.CountryDTO;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    final CountryDao countryDao;


    @Override
    public Integer getCountryCount() {
        return countryDao.getCountrySize();
    }

    @Override
    public Page<Country> getCountries(Integer pageSize, Integer page) {
        return countryDao.getCountries(pageSize, page);
    }

    @Override
    public Page<Country> getCountries(String title, Pageable page) {
       return  countryDao.getCountries(title, page);
    }

    @Override
    public Country getCountry(Long id) {
        return countryDao.getCountry(id);
    }

    @Override
    public Country save(Country country) {
        return countryDao.save(country);
    }

    @Override
    public List<Country> getAllCountries() {
        return countryDao.getAllCountries();
    }
}
