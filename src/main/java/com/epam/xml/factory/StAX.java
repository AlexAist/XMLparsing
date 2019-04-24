package com.epam.xml.factory;

import com.epam.xml.entity.Bank;
import com.epam.xml.entity.Depositor;
import com.epam.xml.entity.StateBank;
import com.epam.xml.exception.BasicException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class StAX implements Factory {

    private final XMLInputFactory factory;
    private List<Bank> bankList;

    public StAX() {
        factory = XMLInputFactory.newInstance();
    }

    @Override
    public List<Bank> getBanks() {
        return bankList;
    }

    @Override
    public void buildBanks(final InputStream xml) throws BasicException {
        bankList = new LinkedList<>();
        XMLStreamReader reader = null;
        try {
            reader = factory.createXMLStreamReader(xml);
            while (reader.hasNext()) {
                if (reader.next() == XMLStreamConstants.START_ELEMENT) {
                    final String tagName = reader.getLocalName();
                    if (XML.getEnumValue(tagName) == XML.STATE_BANK) {
                        bankList.add(buildStateBank(reader));
                    }
                }
            }
        } catch (final XMLStreamException e) {
            throw new BasicException("StAX builder: " + e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (final XMLStreamException e) {
                    throw new RuntimeException("Impossible to close StAX input stream: " + e.getMessage(), e);
                }
            }
        }
    }


    private StateBank buildStateBank(final XMLStreamReader reader) throws XMLStreamException, BasicException {
        final StateBank bank = new StateBank();
        fillBankInfo(bank, reader);
        while (reader.hasNext()) {
            switch (reader.next()) {
                case XMLStreamConstants.START_ELEMENT:
                    if (XML.getEnumValue(reader.getLocalName()) == XML.STATUS) {
                        bank.setStatus(getTextData(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (XML.getEnumValue(reader.getLocalName()) == XML.STATUS) {
                        return bank;
                    }
                    break;
                default:
                    break;
            }
        }

        throw new BasicException("XML file is invalid against XSD");
    }

    private void fillBankInfo(final Bank bank, final XMLStreamReader reader) throws XMLStreamException, BasicException {
        bank.setId(reader.getAttributeValue(null, XML.ID_ATTRIBUTE.getValue()));
        bank.setName(reader.getAttributeValue(null, XML.NAME_ATTRIBUTE.getValue()));
        bank.setCountry(reader.getAttributeValue(null, XML.COUNTRY_ATTRIBUTE.getValue()));
        while (reader.hasNext()) {
            if (reader.next() == XMLStreamConstants.START_ELEMENT) {
                switch (XML.getEnumValue(reader.getLocalName())) {
                    case TYPE:
                        bank.setType(getTextData(reader));
                        break;
                    case ACCOUNT_ID:
                        bank.setAccountId(Integer.parseInt(getTextData(reader)));
                        break;
                    case AMOUNT_ON_DEPOSIT:
                        bank.setAmountOnDeposit(Integer.parseInt(getTextData(reader)));
                        break;
                    case PROFITABILITY:
                        bank.setProfitability(Integer.parseInt(getTextData(reader)));
                        break;
                    case TIME_CONSTRAINS:
                        bank.setTimeConstraints(Integer.parseInt(getTextData(reader)));
                        break;
                    case DEPOSITOR:
                        bank.setDepositor(buildDepositor(reader));
                        break;
                    case CREATE_DATE:
                        bank.setCreateDate(LocalDate.parse(getTextData(reader)));
                        return;
                    default:
                        break;
                }
            }
        }
        throw new BasicException("XML file is invalid against XSD");
    }

    private Depositor buildDepositor(final XMLStreamReader reader) throws XMLStreamException, BasicException {
        final Depositor depositor = new Depositor();
        while (reader.hasNext()) {
            switch (reader.next()) {
                case XMLStreamConstants.START_ELEMENT:
                    switch (XML.getEnumValue(reader.getLocalName())) {
                        case DEPOSITOR_NAME: {
                            depositor.setName(getTextData(reader));
                            break;
                        }
                        case DEPOSITOR_SURNAME: {
                            depositor.setSurname(getTextData(reader));
                        }
                        default:
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT: {
                    final XML tagValue = XML.getEnumValue(reader.getLocalName());
                    if (tagValue == XML.DEPOSITOR) {
                        return depositor;
                    }
                    break;
                }
                default:
                    break;
            }
        }
        throw new BasicException("XML file is invalid against XSD");
    }

    private String getTextData(final XMLStreamReader reader)
            throws XMLStreamException, BasicException {
        if (reader.hasNext()) {
            reader.next();
            return reader.getText();
        }
        throw new BasicException("XML file is invalid against XSD");
    }
}
