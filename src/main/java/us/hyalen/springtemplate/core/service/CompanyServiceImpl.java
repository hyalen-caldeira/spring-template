package us.hyalen.springtemplate.core.service;

import lombok.Setter;
import org.springframework.stereotype.Service;
import us.hyalen.springtemplate.core.BadResponseBodyException;
import us.hyalen.springtemplate.core.ClientException;
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

    CompanyMapper mapper = CompanyMapper.INSTANCE;

    // TODO
    @Override
    public List<CompanyModel> getAllCompanies() {
        throw new BadResponseBodyException("I'm still here ...");
//        return (List<CompanyModel>) dao.findAll();
    }

    @Override
    public CompanyDto findByName(String name) {
        var model = dao.findByName(name).orElseThrow(NotFoundException::new);
        return mapper.mapModelToDto(model);
    }

    @Override
    public void update(CompanyDto dto) {
        validate();
        dao.update(mapper.mapDtoToModel(dto));
    }

    @Override
    public CompanyDto create(CompanyDto dto) {
        validate();
        CompanyModel model = dao.create(CompanyMapper.INSTANCE.mapDtoToModel(dto));
        return mapper.mapModelToDto(model);
    }
}
