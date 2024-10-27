package se331.olympicsbackend.rest.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.olympicsbackend.rest.dao.CountryDao;
import se331.olympicsbackend.rest.entity.Country;

@Service
@RequiredArgsConstructor

public class CountryServiceImpl implements CountryService {
    final CountryDao countryDao;

    @Override
    public Integer getCountrySize() {
        return countryDao.getCountrySize();
    }

    @Override
    public Page<Country> getCountries(String title, Pageable pageable) {
        return countryDao.getCountries(title, pageable);
    }

    @Override
    public Page<Country> getCountries(Integer pageSize, Integer page) {
        return countryDao.getCountries(pageSize, page);
    }

    @Override
    public Country getCountryById(Long id) {
        return countryDao.getCountry(id);
    }

    @Override
    @Transactional
    public Country save(Country country) {
        return countryDao.save(country);
    }


}
