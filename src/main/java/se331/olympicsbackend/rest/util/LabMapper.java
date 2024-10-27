package se331.olympicsbackend.rest.util;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se331.olympicsbackend.rest.entity.Comment;
import se331.olympicsbackend.rest.entity.CommentDTO;
import se331.olympicsbackend.rest.security.user.User;
import org.mapstruct.Mapping;
import se331.olympicsbackend.rest.entity.Country;
import se331.olympicsbackend.rest.entity.CountryDTO;
import se331.olympicsbackend.rest.security.user.UserDTO;

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

    List<CountryDTO> getCountryDto(List<Country> countries);
}
