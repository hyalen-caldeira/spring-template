package us.hyalen.springtemplate.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import us.hyalen.springtemplate.client.response.PaymentParameterInnerResponse;
import us.hyalen.springtemplate.client.response.PaymentParameterInnerResponse;
import us.hyalen.springtemplate.core.dto.CompanyDto;
import us.hyalen.springtemplate.core.dto.PaymentParameterDto;
import us.hyalen.springtemplate.model.CompanyModel;

@Mapper(componentModel = "spring")

public interface PaymentParameterMapper {
    PaymentParameterMapper INSTANCE = Mappers.getMapper(PaymentParameterMapper.class);

    // @Mapping(source = "", target = "", qualifiedByName = "")
    PaymentParameterDto mapResponseToDto(PaymentParameterInnerResponse response);
}
