package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Main {

    private static String setValue() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            String str = sc.next();
            if (str.length() > 0) {
                return str;
            } else {
                System.out.println("Необходимо ввести значение!");
            }
        }
    }

    protected static String[] setValueStudent() {
        String[] strings = new String[6];
        System.out.println("Введите значения для ученика:");
        System.out.println("Введите имя:");
        strings[0] = setValue();
        System.out.println("Введите фамилию:");
        strings[1] = setValue();
        System.out.println("Введите отчество:");
        strings[2] = setValue();
        System.out.println("Введите школу:");
        strings[3] = setValue();
        System.out.println("Введите класс:");
        strings[4] = setValue();
        System.out.println("Введите возраст:");
        strings[5] = getNum() + "";
        return strings;
    }

    private static Student setNewStudent(int size) {
        var strings = setValueStudent();
        return new Student(size + 1, strings[0], strings[1], strings[2],
                strings[3], strings[4], strings[5]);

    }

    private static void changeStudent(String filePath) {
        var sax = new SAXParse();
        var students = sax.readerSaxDocument(filePath);
        System.out.println("Введите id ученика:");
        var searchId = getNum();
        var strings = setValueStudent();
        boolean flag = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == searchId) {
                students.set(i, new Student(searchId,
                        strings[0], strings[1], strings[2],
                        strings[3], strings[4], strings[5]));
                flag = true;
                break;
            }
        }
        if (flag) {
            var dom = new DomParse(filePath);
            dom.setDomNodes(students);
        } else {
            System.out.println("Такого ученика нет!");
        }

    }

    private static void deleteStudent(String filePath) {
        var sax = new SAXParse();
        var students = sax.readerSaxDocument(filePath);
        System.out.println("Введите id ученика:");
        var searchId = getNum();
        boolean flag = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == searchId) {
                students.remove(i);
                flag = true;
                break;
            }
        }
        if (flag) {
            var dom = new DomParse(filePath);
            dom.setDomNodes(students);
        } else {
            System.out.println("Такого студента нет!");
        }

    }

    public static int getNum() {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            System.out.println("Введенно некоректное значение!");
            System.out.print("Введите значение повторно: ");
            sc.next();
        }
        return sc.nextInt();
    }

    public static int choiceWithWork() {
        System.out.println("Выберите с чем работать: \n" +
                "1: XML\n" +
                "2: БД\n" +
                "3: Конвертировать данные из XML в БД\n" +
                "4: Конвертировать данные из БД в XML\n");
        int type = 0;
        while (true) {
            type = getNum();
            if (type >= 1 && type <= 4) {
                return type;
            }
            System.out.println("Можно ввести только 1,2,3,4");
        }
    }

    public static void start() {
        String filePath = "D:\\~~~3курс 2 сем\\~лабы\\ИСИС\\5 лаба ИС\\file.xml";
        int type = choiceWithWork();
        if (type == 3) {
            var sax = new SAXParse();
            var parsing = new Parsing(sax.readerSaxDocument(filePath));
            parsing.parseXMLtoDB();
            start();
        }
        if(type == 4) {
            var sax = new SAXParse();
            var dom = new DomParse(filePath);
            var parsing = new Parsing(sax.readerSaxDocument(filePath), dom);
            parsing.parseDBtoXML();
            start();
        }
        while (true) {
            System.out.println("Выберите действие: \n" +
                    "1: Вывести всё содержимое\n" +
                    "2: Найти содержимое по параметру\n" +
                    "3: Добавить новую запись\n" +
                    "4: Изменить запись\n" +
                    "5: Удалить запись\n" +
                    "9: Выбрать заново с чем работать\n" +
                    "0: Завершить работу\n");
            var choice = getNum();
            switch (choice) {
                case 1: {
                    if (type == 1) {
                        var sax = new SAXParse();
                        var students = sax.readerSaxDocument(filePath);
                        if (students.size() > 0) {
                            for (Student student : students) {
                                System.out.println(student.toString());
                            }
                        }
                    } else if (type == 2) {
                        var mySqlObj = new MySqlParse();
                        var result = mySqlObj.workDataBase(choice);
                        try {
                            while (result.next()) {
                                Student student = new Student(
                                        result.getInt("id"),
                                        result.getString("name"),
                                        result.getString("surname"),
                                        result.getString("patronymic"),
                                        result.getString("school"),
                                        result.getString("clas"),
                                        result.getString("age")
                                );
                                System.out.println(student.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
                case 2: {
                    if (type == 1) {
                        var sax = new SAXParse();
                        int typeSearch = 0;
                        String content = "";
                        System.out.print("Выберите содержимое поиска (id):\n");
                        Scanner scanner = new Scanner(System.in);
                        content = scanner.nextLine();
                        var student = sax.searchSaxDocument(filePath, typeSearch, content);
                        System.out.println(student != null ? student.toString() : "Такого студента нет!");
                    } else if (type == 2) {
                        var mySqlObj = new MySqlParse();
                        var result = mySqlObj.workDataBase(choice);
                        try {
                            while (result.next()) {
                                Student student = new Student(
                                        result.getInt("id"),
                                        result.getString("name"),
                                        result.getString("surname"),
                                        result.getString("patronymic"),
                                        result.getString("school"),
                                        result.getString("clas"),
                                        result.getString("age")
                                );
                                System.out.println(student.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
                case 3: {
                    if (type == 1) {
                        var sax = new SAXParse();
                        var students = sax.readerSaxDocument(filePath);
                        var newStudent = setNewStudent(students.size());
                        students.add(newStudent);
                        var dom = new DomParse(filePath);
                        dom.setDomNodes(students);
                    } else if (type == 2) {
                        var mySqlObj = new MySqlParse();
                        mySqlObj.workDataBase(choice);
                    }
                }
                break;
                case 4: {
                    if (type == 1) {
                        changeStudent(filePath);
                    } else if (type == 2) {
                        var mySqlObj = new MySqlParse();
                        mySqlObj.workDataBase(choice);
                    }
                }
                break;
                case 5: {
                    if (type == 1) {
                        deleteStudent(filePath);
                    } else if (type == 2) {
                        var mySqlObj = new MySqlParse();
                        mySqlObj.workDataBase(choice);
                    }
                }
                break;
                case 9: {
                    start();
                }
                break;
                case 0: {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        start();
    }
}
