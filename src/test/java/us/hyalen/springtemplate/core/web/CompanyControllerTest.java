package us.hyalen.springtemplate.core.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import us.hyalen.springtemplate.core.dto.CompanyDto;
import us.hyalen.springtemplate.core.service.CompanyService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CompanyController.class)
@ActiveProfiles("test")
class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    CompanyService companyService;

    @Test
    void getAllCompanies() throws Exception {
        mockMvc.perform(get("/api/companies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CompanyDto.MEDIA_TYPE))
                .andExpect(content().json("[]"));

        verify(companyService, times(1)).getAllCompanies();
    }

    @Test
    void getCompanyByName() throws Exception {
        mockMvc.perform(get("/api/companies/anyname"))
                .andExpect(status().isOk());

        verify(companyService, times(1)).findByName("anyname");
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}