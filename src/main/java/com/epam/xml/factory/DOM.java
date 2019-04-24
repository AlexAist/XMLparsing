package com.epam.xml.factory;

import com.epam.xml.entity.Bank;
import com.epam.xml.entity.Depositor;
import com.epam.xml.entity.StateBank;
import com.epam.xml.exception.BasicException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class DOM implements Factory {

    private final DocumentBuilder builder;
    private List<Bank> bankList;

    public DOM() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Error during SAXBuilder creation: " + e.getMessage(), e);
        }
    }

    @Override
    public void buildBanks(final InputStream xml) throws BasicException {
        bankList = new LinkedList<>();
        Document document;
        try {
            document = builder.parse(xml);
            document.getDocumentElement().normalize();
            final NodeList banks = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < banks.getLength(); i++) {
                if (banks.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final Element bankElement = (Element) banks.item(i);
                    final XML enumValue = XML.getEnumValue(bankElement.getNodeName());
                    if (enumValue == XML.STATE_BANK) {
                        bankList.add(buildStateBank(bankElement));
                    }
                }
            }
        } catch (SAXException | IOException e) {
            throw new BasicException("DOM builder: " + e.getMessage(), e);
        }

    }

    @Override
    public List<Bank> getBanks() {
        return bankList;
    }

    private StateBank buildStateBank(final Element element) {
        final StateBank bank = new StateBank();
        fillBankInfo(bank, element);
        bank.setStatus(element.getElementsByTagName(XML.STATUS.getValue()).item(0).getTextContent());
        return bank;
    }

    private void fillBankInfo(final Bank bank, final Element element) {
        bank.setId(element.getAttribute("id"));
        bank.setName(element.getAttribute("name"));
        bank.setCountry(element.getAttribute("country"));
        bank.setType(element.getElementsByTagName(XML.TYPE.getValue()).item(0).getTextContent());
        bank.setAccountId(Integer.parseInt(element.getElementsByTagName(XML.ACCOUNT_ID.getValue()).item(0).getTextContent()));
        bank.setAmountOnDeposit(Integer.parseInt(element.getElementsByTagName(XML.AMOUNT_ON_DEPOSIT.getValue()).item(0).getTextContent()));
        bank.setDepositor(buildDepositor((Element) element.getElementsByTagName(XML.DEPOSITOR.getValue()).item(0)));
        bank.setProfitability(Integer.parseInt(element.getElementsByTagName(XML.PROFITABILITY.getValue()).item(0).getTextContent()));
        bank.setTimeConstraints(Integer.parseInt(element.getElementsByTagName(XML.TIME_CONSTRAINS.getValue()).item(0).getTextContent()));
        bank.setCreateDate(LocalDate.parse(element.getElementsByTagName(XML.CREATE_DATE.getValue()).item(0).getTextContent()));
    }

    private Depositor buildDepositor(final Element element) {
        Depositor depositor = new Depositor();
        depositor.setName(element.getElementsByTagName(XML.DEPOSITOR_NAME.getValue()).item(0).getTextContent());
        depositor.setSurname(element.getElementsByTagName(XML.DEPOSITOR_SURNAME.getValue()).item(0).getTextContent());
        return depositor;
    }
}
