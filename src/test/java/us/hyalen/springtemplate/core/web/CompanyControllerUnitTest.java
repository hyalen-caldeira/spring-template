package us.hyalen.springtemplate.core.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import us.hyalen.springtemplate.config.TestDataConfig;
import us.hyalen.springtemplate.core.Core;
import us.hyalen.springtemplate.core.dto.CompanyDto;
import us.hyalen.springtemplate.core.service.CompanyService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = { TestDataConfig.class })
@WebMvcTest(controllers = CompanyController.class)
@ActiveProfiles("test")
public class CompanyControllerUnitTest { //extends Core {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    CompanyService companyService;

//    @Override
//    public String insertSqlFilename() {
//        return "sql/tbl-company.v1/company.sql";
//    }
//
//    @Override
//    public String cleanupSqlFilename() {
//        return "sql/tbl-company.v1/company-cleanup.sql";
//    }

    @Test
    public void getAllCompanies() throws Exception {
        mockMvc.perform(get("/api/companies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CompanyDto.MEDIA_TYPE))
                .andExpect(content().json("[]"));

        // verify(companyService, times(1)).getAllCompanies();
    }

    @Test
    public void getCompanyByName() throws Exception {
        mockMvc.perform(get("/api/companies/Accenture"))
                .andExpect(status().is4xxClientError());

        // verify(companyService, times(1)).findByName("Accenture");
    }

}
