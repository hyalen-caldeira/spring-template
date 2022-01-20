package us.hyalen.springtemplate.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.hyalen.springtemplate.dto.CompanyDto;
import us.hyalen.springtemplate.exception.NotFoundException;
import us.hyalen.springtemplate.model.CompanyModel;
import us.hyalen.springtemplate.service.CompanyService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController("companyController_v1")
@RequestMapping(value = "/api/companies", produces = CompanyDto.MEDIA_TYPE)
public class CompanyController {
    private CompanyService service;

    CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CompanyModel>> getAllCompanies() {
        var list = service.getAllCompanies();
        
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<CompanyDto> getCompanyByName(@PathVariable(value = "name") String name) {
        CompanyDto companyDto = service.findByName(name).orElseThrow(NotFoundException::new);

        return ok(companyDto);
    }

    @PutMapping(value = "/{name}", consumes = CompanyDto.MEDIA_TYPE)
    public void update(@Valid @RequestBody CompanyDto dto, @PathVariable(value = "name") String name) {
        service.findByName(name).orElseThrow(NotFoundException::new);
        service.update(dto);
    }
}
