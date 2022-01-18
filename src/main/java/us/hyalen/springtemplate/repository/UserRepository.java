package us.hyalen.springtemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.hyalen.springtemplate.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
}
