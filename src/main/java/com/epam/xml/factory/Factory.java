package com.epam.xml.factory;

import com.epam.xml.entity.Bank;
import com.epam.xml.exception.BasicException;

import java.io.InputStream;
import java.util.List;

public interface Factory {

    void buildBanks(InputStream xml) throws BasicException;
    List<Bank> getBanks();
}
