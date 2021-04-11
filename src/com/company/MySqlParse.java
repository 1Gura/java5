package com.company;
import java.sql.*;

public class MySqlParse {
    MySqlParse() {

    }
    public void workDataBase() {
        String userName = "root";
        String password = "1234";
        String connectionUrl = "jdbc:mysql://localhost:3306/test";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
                System.out.println("Подключение прошло успешно!" );
                //C:\ProgramData\MySQL\MySQL Server 8.0\Data
                Statement statement = (Statement) connection.createStatement();
//                statement.executeUpdate("create table if not exists books (id MEDIUMINT PRIMARY KEY NOT NULL AUTO_INCREMENT,name VARCHAR(30) NOT NULL)");
//                statement.executeUpdate("insert into books (name) values('Inferno')");
//                statement.executeUpdate("insert into books (name) values('War and Piece')");
                ResultSet resultSet = statement.executeQuery("select * from books");
                while(resultSet.next()) {
                    System.out.print(resultSet.getInt("id") + " ");
                    System.out.println(resultSet.getString("name"));
                }


            } catch (Exception e) {
                System.out.println("Что-то пошло не так.");
            }
        } catch (Exception e) {
            System.out.println("Отсутствует драйвер! ");
        }
    }

}
