package se331.olympicsbackend.rest.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.repository.CountryRepository;

@Repository
@RequiredArgsConstructor
@Profile("db")
public class CountryDaoImpl implements CountryDao {
    final CountryRepository countryRepository;

    @Override
    public Integer getCountrySize() {
        return Math.toIntExact(countryRepository.count());
    }

    @Override
    public Page<Country> getCountries(Integer pageSize, Integer page) {
        return  countryRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    //finding using only some word
    @Override
    public Page<Country> getCountries(String countryName, Pageable page) {
        return countryRepository.findByCountryNameContaining(countryName,page);
    }


    @Override
    public Country getCountry(Long id) {
        return countryRepository.findById(id).orElse(null);
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }
}
