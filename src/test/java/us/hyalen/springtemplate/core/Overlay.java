package us.hyalen.springtemplate.core;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import java.lang.invoke.MethodHandles;
import java.net.URL;

// The following annotation allows using a non static @BeforeAll and @AfterAll
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class Overlay {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    protected String insertSqlFilename;
    protected String cleanupSqlFilename;

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier("transactionManager")
    private PlatformTransactionManager transactionManager;


    @BeforeAll
    public void setupOverlay() {
        if (insertSqlFilename != null) {
            overlaySql();
            log.info("--------->>> SQL H2 OVERLAY APPLIED");
        }
    }

    @AfterAll
    public void cleanupOverlay() {
        if (cleanupSqlFilename != null) {
            cleanupSql();
            log.info("--------->>> SQL H2 CLEANUP APPLIED");
        }
    }

    private void overlaySql() {
        TransactionStatus transaction = transactionManager.getTransaction(null);

        sessionFactory.getCurrentSession()
                .createNativeQuery(readFromUrl(this.getClass().getClassLoader().getResource(insertSqlFilename)))
                .executeUpdate();

        transactionManager.commit(transaction);
    }

    private void cleanupSql() {
        TransactionStatus transaction = transactionManager.getTransaction(null);

        sessionFactory.getCurrentSession()
                .createNativeQuery(readFromUrl(this.getClass().getClassLoader().getResource(cleanupSqlFilename)))
                .executeUpdate();

        transactionManager.commit(transaction);
    }

    private String readFromUrl(URL url) {
        try {
            return Resources.toString(url, Charsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
