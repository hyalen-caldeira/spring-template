package us.hyalen.springtemplate.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import us.hyalen.springtemplate.model.CompanyModel;
import us.hyalen.springtemplate.service.CompanyService;

import java.util.List;

@RestController
public class CompanyController {
    private CompanyService service;

    CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping("/company")
    public ResponseEntity<List<CompanyModel>> getAllCompanies() {
        var list = service.getAllCompanies();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
