package com.expedia.payment.vault.client.example.model;

import com.expedia.payment.vault.client.example.model.InputData;

public class ResponseBean {
    String brandtype;
    String bin;
    InputData inputData;
    public ResponseBean(String brandtype,String bin,InputData inputData){
        this.bin=bin;
        this.brandtype=brandtype;
        this.inputData=inputData;
    }
    public String getBrandtype(){
        return this.brandtype;
    }

    public InputData getInputData() {
        return this.inputData;
    }

    public String getBin() {
        return bin;
    }
}
