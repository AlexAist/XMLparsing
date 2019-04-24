package com.epam.xml.Validator;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ValidatorTest {

    @Test
    public void testIsFileExist() {
        assertTrue(Validator.isFileExist("web/data/banks.xml"));
    }

    @Test
    public void testCheckXMLDocument() {
        assertTrue(Validator.checkXMLDocument("web/data/banks.xml",
                "web/data/banks.xsd"),"Error in CheckXml function!");
    }
}