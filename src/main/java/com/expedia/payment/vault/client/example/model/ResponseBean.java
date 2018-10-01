package com.expedia.payment.vault.client.example.model;

import com.expedia.payment.vault.client.example.model.InputData;

public class ResponseBean {
    String brandtype;
    InputData inputData;
    public ResponseBean(String brandtype,InputData inputData){
        this.brandtype=brandtype;
        this.inputData=inputData;
    }
    public String getBrandtype(){
        return this.brandtype;
    }

    public InputData getInputData() {
        return this.inputData;
    }
}
