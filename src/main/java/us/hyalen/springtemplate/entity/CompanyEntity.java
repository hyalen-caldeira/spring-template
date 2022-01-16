package us.hyalen.springtemplate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.hyalen.springtemplate.entity.audit.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(
        name = "COMPANY",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME"})}
)
@Getter
@Setter
@NoArgsConstructor
public class CompanyEntity extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;

    @NotBlank
    @Size(max = 40)
    @Column (name = "NAME")
    private String name;

//    @Column (name = "ADDRESS")
//    @Size(max = 40)
//    private String address;
//
//    @Column (name = "CITY")
//    @Size(max = 40)
//    private String city;
//
//    @Column (name = "STATE")
//    @Size(max = 20)
//    private String state;
//
//    @Column (name = "PHONE")
//    @Size(max = 12)
//    private String phone;
}
