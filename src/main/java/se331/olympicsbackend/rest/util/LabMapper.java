package se331.olympicsbackend.rest.util;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se331.olympicsbackend.rest.entity.*;
import se331.olympicsbackend.rest.security.user.User;
import se331.olympicsbackend.rest.security.user.UserDTO;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface LabMapper {
    LabMapper INSTANCE= Mappers.getMapper(LabMapper.class);

    UserDTO getUserDTO(User user);
    List<UserDTO> getUserDTO(List<User> users);

    CommentDTO getCommentDTO(Comment comment);
    List<CommentDTO> getCommentDTO(List<Comment> comments);


    // Adjusted mappings for proper field names
    @Mapping(source = "medal.gold_medals", target = "gold_medals")
    @Mapping(source = "medal.silver_medals", target = "silver_medals")
    @Mapping(source = "medal.bronze_medals", target = "bronze_medals")
    @Mapping(source = "medal.total_medals", target = "total_medals")
    @Mapping(source = "medal.ranking", target = "ranking")
    @Mapping(source = "medal.totalRank", target = "totalRank")

    CountryDTO getCountryDto(Country country);
    List<CountryDTO> getCountryDto(List<Country> countries);

    // Mapping fields between Sport and SportDTO
    @Mapping(source = "sportName", target = "sportName")
    @Mapping(source = "gold", target = "gold")
    @Mapping(source = "silver", target = "silver")
    @Mapping(source = "bronze", target = "bronze")
    @Mapping(source = "total", target = "total")
    // Map Sport to SportDTO
    SportDTO toSportDTO(Sport sport);

    // Map lists of countries and sports
    List<CountryDTO> toCountryDTOs(List<Country> countries);
    List<SportDTO> toSportDTOs(List<Sport> sports);
}
