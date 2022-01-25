package us.hyalen.springtemplate.core.web;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import us.hyalen.springtemplate.core.Core;
import us.hyalen.springtemplate.core.dto.CompanyDto;
import us.hyalen.springtemplate.core.service.CompanyService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(CompanyController.class)
// The following annotation allows using a non-static @BeforeAll and @AfterAll
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class CompanyControllerIntegrationTwoTest extends Core {
    private final String EXIST_NAME = "Accenture";
    private final String BASE_URI = "/api/companies/";

    // @MockBean
    // CompanyService service;

    @Override
    public String insertSqlFilename() {
        return "sql/tbl-company.v1/company.sql";
    }

    @Override
    public String cleanupSqlFilename() {
        return "sql/tbl-company.v1/company-cleanup.sql";
    }

    @Test
    public void when_AGetWithCorrectUsernameIsPerformed_then_AValidResourceIsReturned() throws Exception {
        // GIVEN a valid username
        String name = EXIST_NAME;

        // WHEN a get request is made specifying a valid id parameter
//        ResultActions result = mockMvc.perform(get(BASE_URI))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(CompanyDto.MEDIA_TYPE))
//                .andExpect(content().json("[]"));

        // verify(service, times(1)).getAllCompanies();

        // WHEN a get request is made specifying a valid id parameter
        // TODO
        ResultActions result = getRequest(name);

        // THEN, success response is returned
        // TODO
        result.andExpect(status().isOk());

        // AND the company that we get back is as expected
        // TODO
//        String json = result.andReturn().getResponse().getContentAsString();
//        CompanyDto dto = objectMapper.readValue(json, CompanyDto.class);
//
//        assertEquals(EXIST_NAME, dto.getName());
//        assertEquals("", dto.getEmail());
    }

    private ResultActions getRequest(String name) throws Exception {
        return mockMvc.perform(get(BASE_URI + name).accept(CompanyDto.MEDIA_TYPE));
    }
}