package com.epam.xml.factory;

import com.epam.xml.entity.Bank;
import com.epam.xml.entity.Depositor;
import com.epam.xml.entity.StateBank;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class SAXHandler extends DefaultHandler {

    private static final Logger LOGGER = LogManager.getLogger();
    private List<Bank> banks;
    private Bank currentBank;
    private StringBuilder currentTextData;
    private Depositor depositor;

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    public Bank getCurrentBank() {
        return currentBank;
    }

    public void setCurrentBank(Bank currentBank) {
        this.currentBank = currentBank;
    }

    @Override
    public void startDocument() {
        this.banks = new ArrayList<>();
        currentTextData = new StringBuilder();
    }

    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) {
        XML enumValue = XML.getEnumValue(localName);
        switch (enumValue) {
            case STATE_BANK: {
                currentBank = new StateBank();
                break;
            }
            case DEPOSITOR: {
                depositor = new Depositor();
                break;
            }
            default: {
                break;
            }
        }
        if (enumValue == XML.STATE_BANK) {
            currentBank.setId(attributes.getValue(0));
            currentBank.setName(attributes.getValue(1));
            currentBank.setCountry(attributes.getValue(2));
        }
    }

    @Override
    public void characters(final char[] ch, final int start, final int end) {
        currentTextData.append(String.valueOf(ch, start, end).trim());
    }

    @Override
    public void endElement(final String uri, final String localName, final String qName) {
        XML enumValue = XML.getEnumValue(localName);
        if (currentTextData.length() != 0) {
            switch (enumValue) {
                case TYPE: {
                    currentBank.setType(currentTextData.toString());
                    break;
                }
                case DEPOSITOR_NAME: {
                    depositor.setName(currentTextData.toString());
                    break;
                }
                case DEPOSITOR_SURNAME: {
                    depositor.setSurname(currentTextData.toString());
                    break;
                }
                case ACCOUNT_ID: {
                    currentBank.setAccountId(Integer.parseInt(currentTextData.toString()));
                    break;
                }
                case CREATE_DATE: {
                    currentBank.setCreateDate(LocalDate.parse(currentTextData.toString()));
                    break;
                }
                case AMOUNT_ON_DEPOSIT: {
                    currentBank.setAmountOnDeposit(Integer.parseInt(currentTextData.toString()));
                    break;
                }
                case PROFITABILITY: {
                    currentBank.setProfitability(Integer.parseInt(currentTextData.toString()));
                    break;
                }
                case TIME_CONSTRAINS: {
                    currentBank.setTimeConstraints(Integer.parseInt((currentTextData.toString())));
                    break;
                }
                case STATUS: {
                    ((StateBank) currentBank).setStatus(currentTextData.toString());
                    break;
                }
                default: {
                    break;
                }
            }
            currentTextData.setLength(0);
        } else {
            if (enumValue == XML.STATE_BANK) {
                banks.add(currentBank);
            } else if (enumValue == XML.DEPOSITOR) {
                currentBank.setDepositor(depositor);
            }
        }
    }

    @Override
    public void endDocument() {
        LOGGER.log(Level.DEBUG, "Parsing end");
    }
}
