package us.hyalen.springtemplate.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.hyalen.springtemplate.model.CompanyModel;

import java.util.List;
import java.util.Optional;

@Repository
// public interface CompanyRepository extends CrudRepository<CompanyEntity, Long> {
public interface CompanyRepository extends JpaRepository<CompanyModel, Long> {
    Optional<CompanyModel> findByName(String name);
    Optional<CompanyModel> findByEmail(String email);
    Optional<CompanyModel> findByNameOrEmail(String name, String email);
    List<CompanyModel> findByIdIn(List<Long> userIds);

    Boolean existsByName(String name);
    Boolean existsByEmail(String email);
}
