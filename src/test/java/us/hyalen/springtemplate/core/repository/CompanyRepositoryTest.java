package us.hyalen.springtemplate.core.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import us.hyalen.springtemplate.core.dto.CompanyDto;
import us.hyalen.springtemplate.core.mapper.CompanyMapper;
import us.hyalen.springtemplate.model.CompanyModel;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CompanyRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByName() {
        CompanyDto dto = new CompanyDto(4L, "cFive", "Main String", "cfive@example.com");
        CompanyModel model = CompanyMapper.INSTANCE.mapDtoToModel(dto);

        model.setCreated_at(Instant.now());
        model.setUpdated_at(Instant.now());

        testEntityManager.merge(model);
        // testEntityManager.persist(CompanyMapper.INSTANCE.mapDtoToModel(otherDto));

        model = companyRepository.findByName("cFive").get();
        assertEquals("cFive", model.getName());
    }

    @Test
    void findByEmail() {
    }

    @Test
    void findByNameOrEmail() {
    }

    @Test
    void findByIdIn() {
    }

    @Test
    void existsByName() {
    }

    @Test
    void existsByEmail() {
    }
}