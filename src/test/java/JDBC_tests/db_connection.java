package JDBC_tests;

import java.sql.*;

public class db_connection {
    public static void main(String[] args) throws SQLException {

        String db_Url = "jdbc:mysql://localhost/sampledb";
        String db_userName = "root";
        String db_password = "1234567";


        //create our connection

        Connection connection = DriverManager.getConnection(db_Url,db_userName,db_password);

        //create our statement

        Statement statement = connection .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        //execute our sql query

        ResultSet resultSet = statement.executeQuery("Select * from customers");

        resultSet.next();

        System.out.println("customer number:" + resultSet.getInt(1) +
                ", customer name:" + resultSet.getString(2) + ", customer lastName:" + resultSet.getString(3));

        resultSet.next();
        System.out.println("postalCode:" + resultSet.getInt(10)+ ", creditLimit:" + resultSet.getInt(13));


        resultSet.close();
        statement.close();
        connection.close();

    }
}
