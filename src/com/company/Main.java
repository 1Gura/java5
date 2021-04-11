package com.company;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Main {

    public static void main(String[] args) {
        //DOM XML
//        var domTest = new DomParse("D:\\~~~3курс 2 сем\\~лабы\\ИСИС\\5 лаба ИС\\file.xml");
//        domTest.getDOMNodes();
//        domTest.setDomNodes();

        //SAX XML
        //"D:\\~~~3курс 2 сем\\~лабы\\ИСИС\\5 лаба ИС\\saxfile1.xml"
        var saxTest = new SAXParse();
        //saxTest.readerSaxDocument("D:\\~~~3курс 2 сем\\~лабы\\ИСИС\\5 лаба ИС\\saxfile1.xml");
//        saxTest.writeSaxDocument("D:\\~~~3курс 2 сем\\~лабы\\ИСИС\\5 лаба ИС\\saxfile2.xml");
//        PropertiesParse propertiesParse = new PropertiesParse();
//        propertiesParse.readSettings("D:\\~~~3курс 2 сем\\~лабы\\ИСИС\\5 лаба ИС\\settings.properties");

//        Работа с БД
        var base = new MySqlParse();
        base.workDataBase();
    }
}
