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
    private final DefaultHandler handler =  new DefaultHandler() {
        String tag = "";
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            tag = qName;
            if (tag.equalsIgnoreCase("Language"))
                System.out.println("\nЭлемент "+ tag);
        }

        @Override
        public void characters(char ch[], int start, int length) throws SAXException {
            if (tag.equalsIgnoreCase("name"))
                System.out.println(tag + ": " + new String(ch, start, length));
            else
            if (tag.equalsIgnoreCase("age"))
                System.out.println(tag + ": " + new String(ch, start, length));
        }
        @Override
        public void endElement(String uri,String localName,String qName) throws SAXException {
            tag = "";
        }
    };

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
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
};

