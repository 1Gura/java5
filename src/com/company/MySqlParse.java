package com.company;

import java.sql.*;

public class MySqlParse {
    private final String userName = "root";
    private final String password = "1234";
    private final String connectionUrl = "jdbc:mysql://localhost:3306/test";

    MySqlParse() {

    }
    //statement.executeUpdate("INSERT INTO students values (2,'asd','asd','asd','Школа №13', '11', '18');");

    public ResultSet getAll(Statement statement) throws SQLException {
        return statement.executeQuery("select * from students");
    }

    public ResultSet searchRecord(Statement statement, int id) throws SQLException {
        return statement.executeQuery("select * from students where id in(" + id + ");");
    }

    public void addNewRecord(Statement statement) throws SQLException {
        var strings = Main.setValueStudent();
        statement.executeUpdate("INSERT INTO students (name, surname, patronymic, school, clas , age)" +
                " VALUES ('" + strings[0] + "','" + strings[1] + "', '" + strings[2] + "', '" + strings[3] + "', " +
                "'" + strings[4] + "','" + strings[5] + "')");
    }

    public ResultSet workDataBase(int action) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
                System.out.println("Подключение прошло успешно!");
                Statement statement = (Statement) connection.createStatement();
                switch (action) {
                    case 1 -> {
                        return getAll(statement);
                    }
                    case 2 -> {
                        System.out.println("Введите Id записи");
                        var id = Main.getNum();
                        return searchRecord(statement, id);
                    }
                    case 3 -> {
                        addNewRecord(statement);
                    }
                    case 4 -> {
                        //updateRecord();
                    }
                    case 5 -> {
                        //deleteRecord();
                    }
                }


//                ResultSet resultSet = statement.executeQuery("select * from books");
//                while(resultSet.next()) {
//                    System.out.print(resultSet.getInt("id") + " ");
//                    System.out.println(resultSet.getString("name"));
//                }
            } catch (Exception e) {
                System.out.println("Что-то пошло не так.");
            }
        } catch (Exception e) {
            System.out.println("Отсутствует драйвер! ");
        }
        return null;
    }

}
