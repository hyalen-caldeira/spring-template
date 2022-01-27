package us.hyalen.springtemplate.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import us.hyalen.springtemplate.model.audit.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(
        name = "TBL_COMPANY"
        ,uniqueConstraints = {
        @UniqueConstraint(columnNames = {"NAME"})
}
        ,schema = "PORTFOLIO_DB"
)
@Getter
@Setter
@NoArgsConstructor
public class CompanyModel extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", unique = true, nullable = false)
    private long id;

    @NotBlank
    @Size(max = 40)
    @Column (name = "NAME", nullable = false)
    private String name;

    @Column (name = "ADDRESS")
    @Size(max = 40)
    private String address;
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

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;
}
