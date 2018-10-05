package com.expedia.payment.vault.client.example;

import com.expedia.e3.es.payment.common.rest.client.error.BaseClientException;
import com.expedia.e3.es.payment.vault.client.PaymentVaultClient;
import com.expedia.e3.es.payment.vault.common.model.PaymentInstrument;
import com.expedia.payment.vault.client.example.REST.RestClientFactory;

import java.util.UUID;

public class test {
    public static void main(String[] args){

        retrieveInstrument("5D7AFA2D-6092-4E26-87A5-855DFFD8B723");
    }

    private static void retrieveInstrument(String paymentInstrument){
    PaymentVaultClient paymentVaultClient = RestClientFactory.getInstance();
        try {

        PaymentInstrument pi = paymentVaultClient.retrieveInstrument(paymentInstrument.trim(), UUID.randomUUID().toString());
        System.out.println("brand: "+pi.getBrandName()+" "+pi.getBin());

    }
         catch (BaseClientException e) {
        //TODO handle exception for register
             System.out.println(paymentInstrument);
        throw new RuntimeException(e);
    }
}
}
