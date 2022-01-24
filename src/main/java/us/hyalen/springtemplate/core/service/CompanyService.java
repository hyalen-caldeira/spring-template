package us.hyalen.springtemplate.core.service;

import org.springframework.stereotype.Service;
import us.hyalen.springtemplate.core.dto.CompanyDto;
import us.hyalen.springtemplate.model.CompanyModel;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<CompanyModel> getAllCompanies();
    Optional<CompanyDto> findByName(String name);
    void update(CompanyDto dto);
}
