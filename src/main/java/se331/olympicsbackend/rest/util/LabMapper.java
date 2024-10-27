package se331.olympicsbackend.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.entity.CountryDTO;

import java.util.List;

@Mapper
public interface LabMapper {
    LabMapper INSTANCE= Mappers.getMapper(LabMapper.class);

    CountryDTO getCountryDtO(Country country);
    List<CountryDTO> getCountryDto(List<Country> countries);
}
