package com.epam.xml.entity;

import java.time.LocalDate;

public class Bank {

    private String id;
    private String name;
    private String country;
    private String type;
    private Depositor depositor;
    private int accountId;
    private int amountOnDeposit;
    private int profitability;
    private int timeConstraints;
    private LocalDate createDate;

    @Override
    public String toString() {

        return getClass().getName() + "Id:" + this.id + " Name:" + this.name + " Country:"
                + this.country + " Type:" + this.type + " Depositor:" + this.depositor.getName() + " " +
                this.depositor.getSurname()
                + " Account id:" + this.accountId + " Amount on deposit:"
                + this.amountOnDeposit + " Profitability:" + this.profitability +
                " Time constraints:" + this.timeConstraints + " Time:" + this.createDate;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Depositor getDepositor() {
        return depositor;
    }

    public void setDepositor(Depositor depositor) {
        this.depositor = depositor;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public long getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public void setAmountOnDeposit(int amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    public int getProfitability() {
        return profitability;
    }

    public void setProfitability(int profitability) {
        this.profitability = profitability;
    }

    public int getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(int timeConstraints) {
        this.timeConstraints = timeConstraints;
    }
}
