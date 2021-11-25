package ua.goIt.command;

import ua.goIt.config.DbMigration;

public class MigrationCommand implements Command{
    @Override
    public void execute(String... param) {
        DbMigration.migrate();
    }
}
