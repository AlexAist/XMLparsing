package com.epam.xml.factory;

import com.epam.xml.entity.Bank;
import com.epam.xml.entity.Depositor;
import com.epam.xml.entity.StateBank;
import com.epam.xml.exception.BasicException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ParserTest {

    private static List<Bank> banks;

    @BeforeClass
    public static void initialize() {
        banks = new ArrayList<>();
        StateBank bank1 = new StateBank();
        Bank bank2 = new StateBank();
        Depositor depositor1 = new Depositor();
        Depositor depositor2 = new Depositor();
        bank1.setName("BelarusBank");
        bank1.setId("n1");
        bank1.setCountry("Belarus");
        bank1.setType("Metallic");
        depositor1.setName("Alex");
        depositor1.setSurname("Aist");
        bank1.setDepositor(depositor1);
        bank1.setAccountId(12345678);
        bank1.setAmountOnDeposit(1000);
        bank1.setProfitability(12);
        bank1.setTimeConstraints(2);
        bank1.setCreateDate(LocalDate.of(2019,8,4));
        bank1.setStatus("State");

        bank2.setName("BelarusBank");
        bank2.setId("n2");
        bank2.setCountry("Belarus");
        bank2.setType("Metallic");
        depositor2.setName("Alex");
        depositor2.setSurname("Aist");
        bank2.setDepositor(depositor2);
        bank2.setAccountId(12345678);
        bank2.setAmountOnDeposit(1000);
        bank2.setProfitability(12);
        bank2.setTimeConstraints(2);
        bank2.setCreateDate(LocalDate.of(2019,8,4));
        bank1.setStatus("State");
        banks.add(bank1);
        banks.add(bank2);
    }

    @Test
    public void testDOMBuilder() {
        Factory factory = Builder.createBuilder(Type.DOM);
        try {
            factory.buildBanks(Files.newInputStream(Paths.get("web/data/test.xml")));
        } catch (BasicException | IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(banks.toString(), factory.getBanks().toString().trim(),
                "Error in testDOMBuilder function!");
    }

    @Test
    public void testSAXBuilder() {
        Factory factory = Builder.createBuilder(Type.SAX);
        try {
            factory.buildBanks(Files.newInputStream(Paths.get("web/data/test.xml")));
        } catch (BasicException | IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(banks.toString(), factory.getBanks().toString().trim(),
                "Error in testSAXBuilder function!");
    }

    @Test
    public void testStAXBuilder() {
        Factory factory = Builder.createBuilder(Type.STAX);
        try {
            factory.buildBanks(Files.newInputStream(Paths.get("web/data/test.xml")));
        } catch (BasicException | IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(banks.toString(), factory.getBanks().toString().trim(),
                "Error in testStAXBuilder function!");
    }
}