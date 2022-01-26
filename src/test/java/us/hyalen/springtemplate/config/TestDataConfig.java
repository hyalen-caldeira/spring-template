package us.hyalen.springtemplate.config;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

// TODO, Get parameters from property file
@EnableTransactionManagement
@Configuration
@ActiveProfiles("test")
public class TestDataConfig {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment environment;

    @Bean
    @ConfigurationProperties(prefix = "datasource")
    public DataSource h2DataSource() {
        log.info("TestDataConfig, SETTING DATA SOURCE");

        return DataSourceBuilder
                .create()
                .driverClassName("org.h2.Driver")
                // .url("jdbc:h2:mem:portfolio_db")
                .url("jdbc:h2:mem:portfolio_db;init=runscript from 'classpath:sql/schema/portfolio_db.sql';mode=MySql;db_close_on_exit=false")
                .username("sa")
//                .password("sa")
                .build();
    }

    @Bean
    public Properties hibernateProperties() {
        log.info("TestDataConfig, SETTING HIBERNATE PROPERTIES");
        Properties properties = new Properties();

        properties.put("hibernate.jdbc.fetch_size", 500);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.show_sql", true);
        // properties.put("hibernate.connection.url", "jdbc:h2:mem:portfolio_db");
        // properties.put("hibernate.connection.driver_class", "org.h2.Driver");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.validationQuery", "SELECT 1 + 1");
        properties.put("hibernate.hbm2ddl.auto", "none");
        // Without below property, odbcSessionFactory.getCurrentSession() will raise "No CurrentSessionContext configured" exception
        properties.put("hibernate.current_session_context_class","org.springframework.orm.hibernate5.SpringSessionContext");

        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory (
            @Qualifier("hibernateProperties") Properties properties,
            @Qualifier("h2DataSource") DataSource dataSource) {
        log.info("TestDataConfig, SETTING ENTITY MANAGER FACTORY");

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource);
        em.setPackagesToScan("us.hyalen.springtemplate.model");
        em.setPersistenceUnitName("portfolio");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(properties);

        return em;
    }

    @Bean
    @Primary
    public LocalSessionFactoryBean sessionFactory(
            @Qualifier("hibernateProperties") Properties properties,
            @Qualifier("h2DataSource") DataSource dataSource) {
        log.info("TestDataConfig, SETTING SESSION FACTORY");
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setPackagesToScan("us.hyalen.springtemplate.model");

        return localSessionFactoryBean;
    }

    @Bean // (name = "transactionManager")
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        log.info("DataConfig, SETTING TRANSACTION MANAGER");

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }
}
