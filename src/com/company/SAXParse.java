package com.company;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Writer;

public class SAXParse {
    protected int typeSearch;
    protected String content;
    protected boolean flag = false;
    protected int step = 0;
    private final DefaultHandler handler = new DefaultHandler() {
        String tag = "";

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            tag = qName;
            if (tag.equalsIgnoreCase("Student"))
                System.out.println("\nЭлемент " + tag);
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            if (tag.equalsIgnoreCase("name") ||
                    tag.equalsIgnoreCase("surname") ||
                    tag.equalsIgnoreCase("patronymic") ||
                    tag.equalsIgnoreCase("school") ||
                    tag.equalsIgnoreCase("class") ||
                    tag.equalsIgnoreCase("age")
            )
                System.out.println(tag + ": " + new String(ch, start, length));
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            tag = "";
        }
    };

    private final DefaultHandler handlerSearch = new DefaultHandler() {
        String tag = "";

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            tag = qName;
            String id = attributes.getValue("id");
            if (tag.equalsIgnoreCase(content) && typeSearch == 1) {
                flag = true;
                System.out.println("\nЭлемент " + tag);
            }
            if (id != null) {
                if (id.equalsIgnoreCase(content) && typeSearch == 2) {
                    flag = true;
                    System.out.println("\nЭлемент " + tag + " с id =" + content);
                }
            }
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            if (flag) {
                if (tag.equalsIgnoreCase("name") ||
                        tag.equalsIgnoreCase("surname") ||
                        tag.equalsIgnoreCase("patronymic") ||
                        tag.equalsIgnoreCase("school") ||
                        tag.equalsIgnoreCase("class") ||
                        tag.equalsIgnoreCase("age")
                )
                    System.out.println(tag + ": " + new String(ch, start, length));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (tag.equalsIgnoreCase(""))
                flag = false;
            if (tag.equalsIgnoreCase("name") ||
                    tag.equalsIgnoreCase("surname") ||
                    tag.equalsIgnoreCase("patronymic") ||
                    tag.equalsIgnoreCase("school") ||
                    tag.equalsIgnoreCase("class") ||
                    tag.equalsIgnoreCase("age")
            )
                tag = "";
        }
    };

    public void searchSaxDocument(String filePath, int type, String content) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            this.typeSearch = type;
            this.content = content;
            saxParser.parse(new File(filePath), handlerSearch);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readerSaxDocument(String filePath) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File(filePath), this.handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //В качестве аргумента принимает путь файла, в который нужно записать
    public void writeSaxDocument(String filePath) {
        try {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter writer = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(filePath));
            writer.writeStartDocument();
            writer.writeCharacters("\n");
            writer.writeStartElement("root");
            writer.writeCharacters("\n");
            writer.writeStartElement("font");
            writer.writeAttribute("id", "1");
            writer.writeCharacters("TimesNewRoman\n");
            writer.writeEndElement();
            writer.writeEndElement();
            writer.writeEndDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
};

