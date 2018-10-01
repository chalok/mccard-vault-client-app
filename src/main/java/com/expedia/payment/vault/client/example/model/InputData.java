package com.expedia.payment.vault.client.example.model;


import java.util.Date;

public class InputData {


    private   String paymentInstrument;
    private   int expuserid;
    private   int tuidtobelooked;
    private   int tpidtobelooked;
    private   int CreditCardID;
    private String updatedate;
    private String emailAddress;
    private String Database;

    public InputData(String paymentInstrument, int expuserid, int tuidtobelooked, int tpidtobelooked, int creditCardID, String updatedate, String emailAddress, String database) {
        this.paymentInstrument = paymentInstrument;
        this.expuserid = expuserid;
        this.tuidtobelooked = tuidtobelooked;
        this.tpidtobelooked = tpidtobelooked;
        CreditCardID = creditCardID;
        this.updatedate = updatedate;
        this.emailAddress = emailAddress;
        Database = database;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public InputData() {
    }

    public String getDatabase() {
        return Database;
    }

    public void setDatabase(String database) {
        Database = database;
    }

    public void setPaymentInstrument(String paymentInstrument) {
        this.paymentInstrument = paymentInstrument;
    }

    public void setExpuserid(int expuserid) {
        this.expuserid = expuserid;
    }


    public void setTuidtobelooked(int tuidtobelooked) {
        this.tuidtobelooked = tuidtobelooked;
    }

    public void setTpidtobelooked(int tpidtobelooked) {
        this.tpidtobelooked = tpidtobelooked;
    }

    public void setCreditCardID(int creditCardID) {
        CreditCardID = creditCardID;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public String getPaymentInstrument() {
        return paymentInstrument;
    }

    public int getExpuserid() {
        return expuserid;
    }


    public int getTuidtobelooked() {
        return tuidtobelooked;
    }

    public int getTpidtobelooked() {
        return tpidtobelooked;
    }

    public int getCreditCardID() {
        return CreditCardID;
    }

    public String getUpdatedate() {
        return updatedate;
    }
}

