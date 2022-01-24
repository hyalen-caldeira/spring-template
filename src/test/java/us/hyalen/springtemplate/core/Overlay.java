package us.hyalen.springtemplate.core;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public abstract class Overlay {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    protected static String insertSqlFilename;
    protected static String cleanupSqlFilename;

    @BeforeAll
    public static void setupOverlay() {
        if (insertSqlFilename != null) {
            overlaySql();
            log.info("--------->>> SQL H2 OVERLAY APPLIED");
        }
    }

    @AfterAll
    public static void cleanupOverlay() {
        if (cleanupSqlFilename != null) {
            cleanupSql();
            log.info("--------->>> SQL H2 CLEANUP APPLIED");
        }
    }

    private static void overlaySql() {
//        TransactionStatus transaction = hcodeTransactionManager.getTransaction(null);
//
//        hcodeSessionFactory.getCurrentSession()
//                .createNativeQuery(readFromUrl(this.getClass().getClassLoader().getResource(inputSqlFilename())))
//                .executeUpdate();
//
//        hcodeTransactionManager.commit(transaction);
    }

    private static void cleanupSql() {
//        TransactionStatus transaction = hcodeTransactionManager.getTransaction(null);
//
//        hcodeSessionFactory.getCurrentSession()
//                .createNativeQuery(readFromUrl(this.getClass().getClassLoader().getResource(cleanupSqlFilename())))
//                .executeUpdate();
//
//        hcodeTransactionManager.commit(transaction);
    }
}
