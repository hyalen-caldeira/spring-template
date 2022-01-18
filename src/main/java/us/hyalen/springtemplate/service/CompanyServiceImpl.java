package us.hyalen.springtemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.hyalen.springtemplate.model.CompanyModel;
import us.hyalen.springtemplate.mapper.CompanyMapper;
import us.hyalen.springtemplate.repository.CompanyRepository;
import us.hyalen.springtemplate.dto.CompanyDto;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyDao;
    CompanyMapper companyMapper = CompanyMapper.INSTANCE;

    @Override
    public List<CompanyModel> getAllCompanies() {
        return (List<CompanyModel>) companyDao.findAll();
    }

    @Override
    public Optional<CompanyDto> findByName(String name) {
        var model = companyDao.findByName(name);

        return Optional.empty();
        // return Optional.ofNullable(model.isEmpty() ? null : companyMapper.mapModelToResource(model.get()));
    }
}
