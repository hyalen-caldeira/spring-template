package us.hyalen.springtemplate.config.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.hyalen.springtemplate.core.dao.CompanyDao;
import us.hyalen.springtemplate.core.service.CompanyServiceImpl;

import javax.annotation.PostConstruct;

@Component
public class CompanyDaoConfig {
    @Autowired
    @Qualifier("companyDao_v1")
    private CompanyDao companyDao_v1;

    @PostConstruct
    void injectDependencies() {
        CompanyServiceImpl.setDao(companyDao_v1);
    }
}
