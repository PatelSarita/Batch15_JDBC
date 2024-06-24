import org.testng.annotations.Test;

import java.sql.*;

public class JDBC_examples {

    String db_Url = "jdbc:mysql://localhost/sampledb";
    String db_userName = "root";
    String db_password = "1234567";

    @Test
    public void test1() throws SQLException {
        System.out.println("Connection is established");

        Connection connection = DriverManager.getConnection(db_Url, db_userName, db_password);

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = statement.executeQuery("select * from customers;");

        rs.last();

        int rowCount = rs.getRow();
        System.out.println("rowCount = " + rowCount);

        rs.beforeFirst();

        System.out.println("--------------------------------------------------");

        while (rs.next()) {

            System.out.println("customerName: " + rs.getString("customerName"));
        }
//        rs.close();
//        statement.close();
//        connection.close();

    }

    @Test
    public void metaData() throws SQLException {

        Connection connection = DriverManager.getConnection(db_Url, db_userName, db_password);

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = statement.executeQuery("select * from customers;");

        //we are retrieving some detailed info about our database

        DatabaseMetaData databaseMetaData = connection.getMetaData();
        System.out.println("databaseMetaData.getDriverName() = " + databaseMetaData.getDriverName());
        System.out.println("databaseMetaData.getDatabaseProductName() = " + databaseMetaData.getDatabaseProductName());
        System.out.println("databaseMetaData.getURL() = " + databaseMetaData.getURL());
        System.out.println("databaseMetaData.getDriverVersion() = " + databaseMetaData.getDriverVersion());

        // get tables

        ResultSet tables = databaseMetaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            System.out.println("Table: " + tableName);


        }

        System.out.println("------------------------------------------------------");

        ResultSetMetaData rsmd = rs.getMetaData();

        //how many column we have?

        int columnCount= rsmd.getColumnCount();
        System.out.println("columnCount = " + columnCount);

        System.out.println("---------------------------------");

        //we are getting column names

        System.out.println("rsmd.getColumnName(1) = " + rsmd.getColumnName(1));
        System.out.println("rsmd.getColumnName(2) = " + rsmd.getColumnName(2));

        //print all the column names dynamically

        for(int i=1; i<=columnCount;i++){
            System.out.println("Column " + i + " : " + rsmd.getColumnName(i));
        }

        rs.close();
        statement.close();
        connection.close();
    }

}