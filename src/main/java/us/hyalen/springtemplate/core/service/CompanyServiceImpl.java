package us.hyalen.springtemplate.core.service;

import lombok.Setter;
import org.springframework.stereotype.Service;
import us.hyalen.springtemplate.core.Domain;
import us.hyalen.springtemplate.core.NotFoundException;
import us.hyalen.springtemplate.core.dao.CompanyDao;
import us.hyalen.springtemplate.core.dto.CompanyDto;
import us.hyalen.springtemplate.core.mapper.CompanyMapper;
import us.hyalen.springtemplate.model.CompanyModel;

import java.util.List;

@Service
public class CompanyServiceImpl extends Domain implements CompanyService {
    @Setter
    private static CompanyDao dao;

    @Override
    public List<CompanyModel> getAllCompanies() {
        return (List<CompanyModel>) dao.findAll();
    }

    @Override
    public CompanyDto findByName(String name) {
        var model = dao.findByName(name).orElseThrow(NotFoundException::new);

        return CompanyMapper.INSTANCE.mapModelToDto(model);
    }

    @Override
    public void update(CompanyDto dto) {
        validate();
        dao.update(CompanyMapper.INSTANCE.mapDtoToModel(dto));
    }
}
