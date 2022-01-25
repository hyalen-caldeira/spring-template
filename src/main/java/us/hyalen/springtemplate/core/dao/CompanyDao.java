package us.hyalen.springtemplate.core.dao;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.hyalen.springtemplate.model.CompanyModel;
import us.hyalen.springtemplate.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Component("companyDao_v1")
@Transactional
public class CompanyDao {
    @Autowired
    CompanyRepository repository;

    // --------
    @Autowired
    @Qualifier("sessionFactory")
    @Getter
    protected SessionFactory sessionFactory;
    // ---------

    public List<CompanyModel> findAll() {
        return repository.findAll();
    }

    public Optional<CompanyModel> findByName(String name) {
        Optional<CompanyModel> model = repository.findByName(name);

        return model;
    }

    public void update(CompanyModel model) {
        repository.save(model);
    }

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

//    public boolean existsByUsername(String username) {
//        return repository.existsByUsername(username);
//    }
//
//    public Optional<UserEntity> findByUserId(Long userId) {
//        UserModel model = getSessionFactory().getCurrentSession().get(UserModel.class, userId);
//        User.Builder builder = (new User.Builder()).withUserModel(model);
//        return Optional.ofNullable(builder == null ? null : builder.build());
//    }

//    public Optional<User> findByUsernameOrEmail(String username, String email) {
//        Optional<UserModel> model = repository.findByUsernameOrEmail(username, email);
//        User.Builder builder = (new User.Builder()).withUserModel(model.orElse(null));
//        return Optional.ofNullable(builder == null ? null : builder.build());
//    }
//
//    public User create(UserModel model) {
//        getSessionFactory().getCurrentSession().save(model);
//        return new User.Builder().withUserModel(model).build();
//    }
//
//    public void delete(UserModel model) {
//        getSessionFactory().getCurrentSession().delete(model);
//    }
//
//    public List<User> findAllUsers() {
//        return null;
//    }
}
