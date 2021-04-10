package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesParse {
    PropertiesParse() {

    }
    public void readSettings (String path) {
        Properties prop = new Properties();
        try {
            //обращение к файлу и получение данных
            FileInputStream fis = new FileInputStream(path);
            prop.load(fis);
            // взятие свойства и преобразование в необходимую кодировку
            String lab = new String(prop.getProperty("lab").getBytes("ISO8859-1"));
            String fio = new String(prop.getProperty("fio").getBytes("ISO8859-1"));

            //печать полученных данных в консоль
            System.out.println("lab: " + lab + "\nfio: " + fio);
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл не найден");
            e.printStackTrace();
        }

    }

}
