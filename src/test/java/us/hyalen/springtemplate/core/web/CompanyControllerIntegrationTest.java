package us.hyalen.springtemplate.core.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import us.hyalen.springtemplate.core.dto.CompanyDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CompanyControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getCompanyByName() {
        ResponseEntity<CompanyDto> response =
                restTemplate.getForEntity("http://localhost:" + port + "/api/companies/Accenture", CompanyDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getAllCompanies() {
        ResponseEntity<List> response =
                restTemplate.getForEntity("http://localhost:" + port + "/api/companies", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
