package us.hyalen.springtemplate.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import us.hyalen.springtemplate.dto.CompanyDto;
import us.hyalen.springtemplate.model.CompanyModel;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    // ====================== Domain to Model
    CompanyDto mapModelToDto(CompanyModel company, @MappingTarget CompanyDto dto);
}