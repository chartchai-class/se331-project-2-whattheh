package se331.olympicsbackend.rest.util;

import org.mapstruct.factory.Mappers;

public interface LabMapper {
    LabMapper INSTANCE= Mappers.getMapper(LabMapper.class);
}
