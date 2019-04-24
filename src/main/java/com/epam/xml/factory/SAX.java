package com.epam.xml.factory;

import com.epam.xml.entity.Bank;
import com.epam.xml.exception.BasicException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.List;

public class SAX implements Factory {

    private final SAXHandler handler;

    private SAXParser parser;

    private List<Bank> bankList;

    public SAX() {
        handler = new SAXHandler();
        try {
            final SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException("Error during SAXBuilder creation: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Bank> getBanks() {
        return bankList;
    }

    @Override
    public void buildBanks(final InputStream xml) throws BasicException {
        try {
            parser.parse(xml, handler);
            bankList = handler.getBanks();
        } catch (final Exception e) {
            throw new BasicException("SAX builder: " + e.getMessage(), e);
        }
    }

}

