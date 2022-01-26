package us.hyalen.springtemplate.config;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import us.hyalen.springtemplate.interceptor.EventLogInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

//@EnableTransactionManagement
//@Configuration
public class DataConfig {
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private Environment environment;
//
//    @Bean (name = "h2Properties")
//    @Profile({"dev", "integrationTest"})
//    @Primary
//    public Properties hibernateProperties() {
//        log.info("DataConfig, SETTING HIBERNATE PROPERTIES");
//
//        Properties properties = new Properties();
//        properties.put("hibernate.jdbc.fetch_size", 500);
//
//        // Allow System properties to overwrite this, so that in IDE we can set it to show SQL
//        if (System.getProperty("hibernate.show_sql") != null) {
//            properties.put("hibernate.format_sql", true);
//            properties.put("hibernate.show_sql", true);
//        } else {
//            properties.put("hibernate.format_sql", true);
//            properties.put("hibernate.show_sql", true);
//        }
//
//        properties.put("testWhileIdle", true);
//        properties.put("validationQuery", "SELECT 1 + 1");
//        // properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//        properties.put("hibernate.hbm2ddl.auto", "update");
//
//        // Without below property, ocdbSessionFactory.getCurrentSession() will raise "No CurrentSessionContext configured" exception
//        properties.put("hibernate.current_session_context_class","org.springframework.orm.hibernate5.SpringSessionContext");
//
//        return properties;
//    }
//
//    @Bean(name = "h2DataSource")
//    @ConfigurationProperties(prefix = "datasource")
//    @Profile({"dev", "integrationTest"})
//    @Primary
//    public DataSource h2DataSource(@Value("${locale-alias.portfolio}") String alias) {
//        log.info("DataConfig, SETTING H2 DATA SOURCE");
//
//        String driverClassName = environment.getProperty("datasource.driverClassName." + alias);
//        String url = environment.getProperty("datasource.url." + alias);
//        String userName = environment.getProperty("datasource.username." + alias);
//        String password = environment.getProperty("datasource.password." + alias);
//        // Integer port = Integer.parseInt(environment.getProperty("db_port." + alias));
//
//        return DataSourceBuilder
//                .create()
//                .driverClassName(driverClassName)
//                .url(url)
//                .username(userName)
//                .password(password)
//                .build();
//
//        // dataSource.setUrl("jdbc:mysql://" + server + ":" + port + "/" + dbName + "?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false");
//    }
//
//    @Bean(name = "mySqlDataSource")
//    @ConfigurationProperties(prefix = "datasource")
//    @Profile({"prod", "integrationTest"})
//    public DataSource mySqlDataSource()
//    {
//        log.info("DataConfig, SETTING MYSQL DATA SOURCE");
//
//        return DataSourceBuilder
//                .create()
//                .url("jdbc:mysql://localhost/testdb")
//                .username("sa")
//                .password("sa")
//                .build();
//    }
//
//    @Bean // (name = "transactionManager")
//    @Profile({"prod", "integrationTest"})
//    public HibernateTransactionManager transactionManager(@Qualifier("h2SessionFactory") SessionFactory sessionFactory) {
//        log.info("DataConfig, SETTING TRANSACTION MANAGER");
//
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory);
//
//        return transactionManager;
//    }
//
//    @Bean(name = "h2SessionFactory")
//    @Profile({"dev", "integrationTest"})
//    @Primary
//    public LocalSessionFactoryBean sessionFactory(
//            @Qualifier("h2Properties") Properties properties,
//            EventLogInterceptor eventLogInterceptor,
//            @Qualifier("h2DataSource") DataSource dataSource) {
//        log.info("DataConfig, SETTING SESSION FACTORY");
//
//        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
//
//        localSessionFactoryBean.setDataSource(dataSource);
//        localSessionFactoryBean.setHibernateProperties(properties);
//        localSessionFactoryBean.setPackagesToScan("us.hyalen.springtemplate.model");
//        localSessionFactoryBean.setEntityInterceptor(eventLogInterceptor);
//
//        return localSessionFactoryBean;
//    }
//
//    @Bean (name = "entityManagerFactory")
//    @Profile({"dev", "integrationTest"})
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            @Qualifier("h2Properties") Properties properties,
//            @Qualifier("h2DataSource") DataSource dataSource) {
//        log.info("DataConfig, SETTING ENTITY MANAGER FACTORY");
//
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("us.hyalen.springtemplate.model");
//        em.setPersistenceUnitName("portfolio");
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.setJpaProperties(properties);
//
//        return em;
//    }
}
