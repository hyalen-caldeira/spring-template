package us.hyalen.springtemplate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CompanyDto {
    public static final String MEDIA_TYPE = "application/vnd.spring-template.company.v1+json";

    @JsonProperty("id")
    private Long id;

    @NotBlank
    @Size(max = 40)
    @JsonProperty("name")
    private String name;

    @Size(max = 40)
    @JsonProperty("address")
    private String address;

    @NotBlank
    @Email
    @Size(max = 40)
    @JsonProperty("email")
    private String email;
//    @CreatedDate
//    @Column(nullable = false, updatable = false)
//    public Instant CREATED_AT;
//    @LastModifiedDate
//    @Column(nullable = false)
//    public Instant UPDATED_AT;
}
