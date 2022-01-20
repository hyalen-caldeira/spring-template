package us.hyalen.springtemplate.service;

import org.springframework.stereotype.Service;
import us.hyalen.springtemplate.dto.CompanyDto;
import us.hyalen.springtemplate.model.CompanyModel;

import java.util.List;
import java.util.Optional;

@Service
public interface CompanyService {
    List<CompanyModel> getAllCompanies();
    Optional<CompanyDto> findByName(String name);
    void update(CompanyDto dto);
}
