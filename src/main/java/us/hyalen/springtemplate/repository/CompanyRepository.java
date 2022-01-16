package us.hyalen.springtemplate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import us.hyalen.springtemplate.entity.CompanyEntity;

@Repository
public interface CompanyRepository extends CrudRepository<CompanyEntity, Long> {
}
