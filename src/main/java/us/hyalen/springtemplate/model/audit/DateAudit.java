package us.hyalen.springtemplate.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"CREATED_AT", "UPDATED_AT"},
        allowGetters = true
)
@Getter
@Setter
public abstract class DateAudit implements Serializable {
    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Instant created_at;

    @LastModifiedDate
    @Column(name = "UPDATED_AT", nullable = false)
    private Instant updated_at;
}
