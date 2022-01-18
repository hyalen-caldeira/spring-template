package us.hyalen.springtemplate.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CompanyDto {
    public static final String MEDIA_TYPE = "api/vnd.spring-template.company.v1+json";

    public Long id;
    @NotBlank
    @Size(max = 40)
    public String name;
    @Size(max = 40)
    public String address;
    @NotBlank
    @Size(max = 40)
    @Email
    public String email;
}
