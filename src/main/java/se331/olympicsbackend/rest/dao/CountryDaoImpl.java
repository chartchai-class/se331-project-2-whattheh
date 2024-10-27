package se331.olympicsbackend.rest.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.entity.CountryDTO;
import se331.olympicsbackend.rest.entity.Medal;
import se331.olympicsbackend.rest.repository.CountryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CountryDaoImpl implements CountryDao{
    final CountryRepository countryRepository;



    @Override
    public Integer getCountrySize() {
        return Math.toIntExact(countryRepository.count());
    }

    @Override
    public Country getCountry(Integer id) {
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

    @Override
    public List<Country> getAllCountries() {
         return countryRepository.findAll();
    }

//    @Override
//    public List<CountryDTO> getAllCountries() {
//        List<Country> countries = countryRepository.findAll();
//        return countries.stream().map(this::mapToDTO).collect(Collectors.toList());
//    }
//
//    // Map a Country entity to CountryDTO
//    private CountryDTO mapToDTO(Country country) {
//        Medal medal = country.getMedal();  // Get the single medal associated with the country
//
//        return CountryDTO.builder()
//                .id(country.getId())
//                .flag(country.getFlag())
//                .countryName(country.getCountryName())
//                .gold(medal != null ? medal.getGold() : 0)
//                .silver(medal != null ? medal.getSilver() : 0)
//                .bronze(medal != null ? medal.getBronze() : 0)
//                .totalMedals(medal != null ? medal.getTotalMedals() : 0)
//                .ranking(medal != null ? medal.getRanking() : 0)
//                .totalRank(medal != null ? medal.getTotalRank() : 0)
//                .build();
//    }
}

