package us.hyalen.springtemplate.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import us.hyalen.springtemplate.entity.CompanyEntity;
import us.hyalen.springtemplate.service.CompanyService;

import java.util.List;

@RestController
public class CompanyController {
    private CompanyService service;

    @Autowired
    public void setCompanyService(CompanyService service) {
        this.service = service;
    }

//    CompanyController(CompanyService service) {
//        this.service = service;
//    }

    @GetMapping("/company")
    public ResponseEntity<List<CompanyEntity>> getAllCompanies() {
        var list = service.getAllCompanies();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
