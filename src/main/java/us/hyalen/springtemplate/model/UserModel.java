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
        name = "TBL_USER",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"USERNAME"})
                ,@UniqueConstraint(columnNames = {"EMAIL"})
        }
        ,schema = "PORTFOLIO_DB"
)
@Setter
@Getter
@NoArgsConstructor
public class UserModel extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(max = 20)
    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @NotBlank
    @Size(max = 20)
    @Column(name = "LAST_NAME")
    private String lastName;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    @Column(name = "EMAIL")
    private String email;

    @NotBlank
    @Size(max = 40)
    @Column(name = "USERNAME")
    private String username;

    @NotBlank
    @Size(max = 20)
    @Column(name = "PASSWORD")
    private String password;
}
