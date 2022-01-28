package us.hyalen.springtemplate.core.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import us.hyalen.springtemplate.core.dto.CompanyDto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CompanyControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllCompanies() {
//        ResponseEntity<List> response =
//                this.restTemplate.getForEntity("http://localhost:" + port + "/companies/", List.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getCompanyByName() {
//        ResponseEntity<CompanyDto> response =
//                this.restTemplate.getForEntity("http://localhost:" + port + "/api/companies/Accenture", CompanyDto.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}