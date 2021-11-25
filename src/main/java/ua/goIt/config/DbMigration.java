package ua.goIt.config;

import org.flywaydb.core.Flyway;

import java.util.Arrays;

public class DbMigration {

    public static void migrate() {
        Flyway flyway = Flyway. configure()
                .dataSource(DataSourceHolder.getDataSource())
                .baselineOnMigrate(true)
                .locations("classpath:db.migration")
                .load();
        flyway.migrate();
    }
}
