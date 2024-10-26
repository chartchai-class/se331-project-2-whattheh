package se331.olympicsbackend.rest.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.repository.CountryRepository;

import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class CountryDaoImpl implements CountryDao{
    final CountryRepository countryRepository;

    @Override
    public Integer getCountrySize() {
        return Math.toIntExact(countryRepository.count());
    }

    @Override
    public Country getCountry(Long id) {
        return countryRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Country> getCountries(Integer pageSize, Integer page) {
        return countryRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public Page<Country> getCountries(String name, Pageable page) {
        return countryRepository.findByCountryName(name,page);
    }


    @Override
    public Country save(Country country) {
        return  countryRepository.save(country);
    }
}
