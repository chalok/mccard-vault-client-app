package com.expedia.payment.vault.client.example;

import com.expedia.e3.es.payment.common.rest.client.error.BaseClientException;
import com.expedia.e3.es.payment.vault.client.PaymentVaultClient;
import com.expedia.e3.es.payment.vault.common.model.PaymentInstrument;
import com.expedia.payment.vault.client.example.REST.RestClientFactory;

import java.util.UUID;

public class test {
    public static void main(String[] args){

        retrieveInstrument("C354E617-35BF-436A-8C5F-E7F7AB40457E");
    }

    private static void retrieveInstrument(String paymentInstrument){
    PaymentVaultClient paymentVaultClient = RestClientFactory.getInstance();
        try {
        //  System.out.println(paymentVaultClient.retrieveInstrument(paymentInstrument,UUID.randomUUID().toString()));
        // paymentVaultClient.retrieveInstrument(registeredInstrument.getPaymentInstrumentID(), UUID.randomUUID().toString());
        PaymentInstrument pi = paymentVaultClient.retrieveInstrument(paymentInstrument, UUID.randomUUID().toString());
        System.out.println("brand: "+pi.getBrandName()+" "+pi.getBin());

    }
         catch (BaseClientException e) {
        //TODO handle exception for register
        throw new RuntimeException(e);
    }
}
}
