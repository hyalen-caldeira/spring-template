package us.hyalen.springtemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.hyalen.springtemplate.dao.CompanyDao;
import us.hyalen.springtemplate.dto.CompanyDto;
import us.hyalen.springtemplate.mapper.CompanyMapper;
import us.hyalen.springtemplate.model.CompanyModel;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyDao companyDao;
    CompanyMapper companyMapper = CompanyMapper.INSTANCE;

    @Override
    public List<CompanyModel> getAllCompanies() {
        return (List<CompanyModel>) companyDao.findAll();
    }

    @Override
    public Optional<CompanyDto> findByName(String name) {
        var model = companyDao.findByName(name);

        return Optional.ofNullable(model.isEmpty() ? null : companyMapper.mapModelToDto(model.get()));
    }
}
