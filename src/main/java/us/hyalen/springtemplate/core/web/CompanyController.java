package us.hyalen.springtemplate.core.web;

//import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import us.hyalen.springtemplate.core.ApiResponse;
import us.hyalen.springtemplate.core.NotFoundException;
import us.hyalen.springtemplate.core.dto.CompanyDto;
import us.hyalen.springtemplate.core.service.CompanyService;
import us.hyalen.springtemplate.model.CompanyModel;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.created;

@RestController("companyController_v1")
@RequestMapping(value = "/api/companies", produces = CompanyDto.MEDIA_TYPE)
// The following annotation could also be configured on SwaggerConfig.java
//@ApiResponses(value = {
//        @ApiResponse(code=400, message = "This is a bad request, please follow the API documentation for the proper request format."),
//        @ApiResponse(code=401, message = "Due to security constraints, your access request cannot be authorized."),
//        @ApiResponse(code=500, message = "The server is down. Please make sure that the Company microservice is running.")
//})
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
        CompanyDto companyDto = service.findByName(name);

        return ok(companyDto);
    }

    @PutMapping(value = "/{name}", consumes = CompanyDto.MEDIA_TYPE)
    public void update(@Valid @RequestBody CompanyDto dto, @PathVariable(value = "name") String name) {
        service.findByName(name);
        service.update(dto);
    }

    @PostMapping(consumes = CompanyDto.MEDIA_TYPE)
    // public void create(@Valid @RequestBody UserResource resource, HttpServletResponse httpServletResponse) {
    public ResponseEntity<?> create(@Valid @RequestBody CompanyDto dto) {
        dto = service.create(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/companies/{name}")
                .buildAndExpand(dto.getName()).toUri();

        return created(location).body(new ApiResponse(true, ApiResponse.CREATED));

        // httpServletResponse.setHeader("Location", fromMethodCall(on(getClass()).getCompanyById(dto.getId())).build().toString());
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<?> delete(@PathVariable(value = "name") String name) {
        service.findByName(name);
//        service.delete();

        // Not using swagger version of API response
        return ok(new ApiResponse(true, ApiResponse.DELETED));
    }
}
