package com.company;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {

    public static void main(String[] args) {
        //DOM XML
        var domTest = new DomParse("D:\\~~~3курс 2 сем\\~лабы\\ИСИС\\5 лаба ИС\\file.xml");
        domTest.getDOMNodes();
        domTest.setDomNodes();
    }
}
