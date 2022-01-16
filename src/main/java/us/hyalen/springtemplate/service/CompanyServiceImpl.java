package us.hyalen.springtemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.hyalen.springtemplate.entity.CompanyEntity;
import us.hyalen.springtemplate.repository.CompanyRepository;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<CompanyEntity> getAllCompanies() {
        return (List<CompanyEntity>) companyRepository.findAll();
    }
}
