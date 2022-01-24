package us.hyalen.springtemplate.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import us.hyalen.springtemplate.core.dto.CompanyDto;
import us.hyalen.springtemplate.model.CompanyModel;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    // ====================== Model to DTO
    CompanyDto mapModelToDto(CompanyModel model);

    // DTO to Model
    CompanyModel mapDtoToModel(CompanyDto dto);
}