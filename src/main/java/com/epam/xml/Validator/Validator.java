package com.epam.xml.Validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class Validator {

    private static final Logger LOGGER = LogManager.getLogger();

    public static boolean isFileExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public static boolean checkXMLDocument(String fileName, String schemaName) {
        Source source = new StreamSource(fileName);
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        boolean flag;
        try {
            Schema schema = factory.newSchema(new File(schemaName));
            javax.xml.validation.Validator validator = schema.newValidator();
            validator.validate(source);
            flag = true;
        } catch (SAXException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            flag = false;
        } catch (IOException e1) {
            flag = false;
            LOGGER.log(Level.ERROR, e1.getMessage());
        }

        return flag;
    }
}
