package us.hyalen.springtemplate.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

import us.hyalen.springtemplate.dto.CompanyDto;
import us.hyalen.springtemplate.exception.NotFoundException;
import us.hyalen.springtemplate.model.CompanyModel;
import us.hyalen.springtemplate.service.CompanyService;

import java.util.List;
import java.util.Optional;

@RestController("companyController_v1")
@RequestMapping(value = "/api/companies", produces = CompanyDto.MEDIA_TYPE)
public class CompanyController {
    private CompanyService service;

    CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<CompanyDto> getCompanyByName(@PathVariable(value = "name") String name) {
//        Optional<CompanyDto> companyDto = service.findByName(name).orElseThrow(NotFoundException::new);
        CompanyDto companyDto = service.findByName(name).orElseThrow(NotFoundException::new);

        return ok(companyDto);
    }

    @GetMapping
    public ResponseEntity<List<CompanyModel>> getAllCompanies() {
        var list = service.getAllCompanies();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
