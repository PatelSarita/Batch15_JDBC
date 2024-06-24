package JDBC_tests;

import org.testng.annotations.Test;

import java.sql.*;

public class CRUD_examples {

    String db_Url = "jdbc:mysql://localhost/employees";
    String db_userName = "root";
    String db_password = "1234567";

    @Test
    public void createRecord() throws SQLException {
        Connection connection = DriverManager.getConnection(db_Url,db_userName,db_password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

        String query ="INSERT INTO customers(customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,addressLine2,city_id,town_id,postalCode,country_id,salesRepEmployeeNumber,creditLimit)\n" +
                "\tValues(556,'Google','Tech','Euro ','40.32.2555','54, London','United Kingdom',1,1,44000,7,1370,21000.00);";

        int rowsAffected = statement.executeUpdate(query);
        statement.close();
        connection.close();

    }

    @Test
    public void updateRecord() throws SQLException {
        Connection connection = DriverManager.getConnection(db_Url,db_userName,db_password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

        String expectedCustomerName="Dell Inc.";
        String queryOne="update customers Set customerName = '"+expectedCustomerName+"' where customerName= 'Sarita';";

        int rowsAffected = statement.executeUpdate(queryOne);

        String queryTwo= "select * from customers where customerName = 'Dell Inc.';";

        ResultSet resultSet = statement.executeQuery(queryTwo);
        resultSet.next();

        System.out.println("updated customer name is: " + resultSet.getString("customerName"));

    }
    @Test void deleteRecord() throws SQLException {
        Connection connection = DriverManager.getConnection(db_Url,db_userName,db_password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

        String deleteQuery = "delete from employees where emp_no=10003;";

        int rowsAffected = statement.executeUpdate(deleteQuery);
        System.out.println("rowsAffected = " + rowsAffected);



    }

}
