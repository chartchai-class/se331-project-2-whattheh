package se331.olympicsbackend.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.entity.CountryDTO;
import se331.olympicsbackend.rest.security.user.User;
import se331.olympicsbackend.rest.security.user.UserDTO;

import java.util.List;

@Mapper
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);

    // Adjusted mappings for proper field names
    @Mapping(source = "medal.gold", target = "gold")
    @Mapping(source = "medal.silver", target = "silver")
    @Mapping(source = "medal.bronze", target = "bronze")
    @Mapping(source = "medal.totalMedals", target = "totalMedals")
    @Mapping(source = "medal.ranking", target = "rank")
    @Mapping(source = "medal.totalRank", target = "totalRank")
    CountryDTO getCountryDto(Country country);

    List<CountryDTO> getCountryDto(List<Country> countries);

    UserDTO getUserDTO(User user);

    List<UserDTO> getUserDTO(List<User> users);
}
