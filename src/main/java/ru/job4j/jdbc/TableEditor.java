package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Statement statement;
    private final Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
        initStatement();
    }

    private void initConnection() throws Exception {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driver_class");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        Class.forName(driverClass);
        connection = DriverManager.getConnection(url, username, password);
    }

    private void initStatement() throws SQLException {
        statement = connection.createStatement();
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s();", tableName
        );
        statement.execute(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "DROP TABLE IF EXISTS %s;", tableName
        );
        statement.execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type
        );
        statement.execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s;", tableName, columnName
        );
        statement.execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName
        );
        statement.execute(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        var selection = statement.executeQuery(String.format(
                "SELECT * FROM %s LIMIT 1", tableName
        ));
        var metaData = selection.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            buffer.add(String.format("%-15s|%-15s%n",
                    metaData.getColumnName(i), metaData.getColumnTypeName(i))
            );
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        try (TableEditor tableEditor = new TableEditor(new Properties())) {
            tableEditor.createTable("demo_table");
            System.out.println(tableEditor.getTableScheme("demo_table"));
            System.out.println();
            tableEditor.addColumn("demo_table", "id", "SERIAL PRIMARY KEY");
            tableEditor.addColumn("demo_table", "name", "TEXT");
            System.out.println(tableEditor.getTableScheme("demo_table"));
            System.out.println();
            tableEditor.renameColumn("demo_table", "name", "username");
            System.out.println(tableEditor.getTableScheme("demo_table"));
            System.out.println();
            tableEditor.dropColumn("demo_table", "username");
            System.out.println(tableEditor.getTableScheme("demo_table"));
            System.out.println();
            tableEditor.dropTable("demo_table");
            System.out.println("Table is dropped");
        }
    }
}