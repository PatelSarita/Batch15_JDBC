import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfMap_example {

    String db_Url = "jdbc:mysql://localhost/employees";
    String db_userName = "root";
    String db_password = "1234567";

    @Test
    public void listOfMapExample(){

        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String,Object> row1 = new HashMap<>();
        row1.put("emp_no","10002");
        row1.put("first_name","Sarita");
        row1.put("last_name","patel");
        row1.put("gender","F");

        System.out.println("row1 = " + row1.toString());

        Map<String,Object> row2 = new HashMap<>();
        row2.put("emp_no","10003");
        row2.put("first_name","Priya");
        row2.put("last_name","singh");
        row2.put("gender","F");

        System.out.println("row2 = " + row2.toString());

        queryData.add(row1);
        queryData.add(row2);

        System.out.println("firstName of the customer = " + queryData.get(0).get("first_name"));
    }

    @Test
    public void listOfMapExample2() throws SQLException {

        Connection connection = DriverManager.getConnection(db_Url,db_userName,db_password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = statement.executeQuery("select emp_no,first_name,last_name,gender from employees limit 5;");

        ResultSetMetaData rsmd = resultSet.getMetaData();

        resultSet.next();

        List<Map<String,Object>> myList = new ArrayList<>();

        Map<String,Object> row1 = new HashMap<>();

        row1.put(rsmd.getColumnName(1),resultSet.getString(1));
        row1.put(rsmd.getColumnName(2),resultSet.getString(2));
        row1.put(rsmd.getColumnName(3),resultSet.getString(3));
        row1.put(rsmd.getColumnName(4),resultSet.getString(4));

        System.out.println("row1.toString() = " + row1.toString());

        resultSet.next();

        Map<String,Object> row2 = new HashMap<>();

        row2.put(rsmd.getColumnName(1),resultSet.getString(1));
        row2.put(rsmd.getColumnName(2),resultSet.getString(2));
        row2.put(rsmd.getColumnName(3),resultSet.getString(3));
        row2.put(rsmd.getColumnName(4),resultSet.getString(4));

        System.out.println("row2.toString() = " + row2.toString());
        myList.add(row1);
        myList.add(row2);

        System.out.println("gender of the first customer = " + myList.get(0).get("gender"));
        System.out.println("myList.get(1).get(\"last_name\") = " + myList.get(1).get("last_name"));

    }

}
