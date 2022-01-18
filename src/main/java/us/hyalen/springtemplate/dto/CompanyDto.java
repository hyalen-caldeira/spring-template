package us.hyalen.springtemplate.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
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
//    @CreatedDate
//    @Column(nullable = false, updatable = false)
//    public Instant CREATED_AT;
//    @LastModifiedDate
//    @Column(nullable = false)
//    public Instant UPDATED_AT;
}
