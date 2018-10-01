package com.expedia.payment.vault.client.example.REST;


import com.expedia.e3.es.payment.common.rest.client.error.BaseClientException;
import com.expedia.e3.es.payment.vault.client.PaymentVaultClient;
import com.expedia.e3.es.payment.vault.common.model.PaymentInstrument;
import com.expedia.payment.vault.client.example.model.InputData;
import com.expedia.payment.vault.client.example.model.ResponseBean;

import java.util.UUID;
import java.util.concurrent.Callable;


public class HttpTask implements Callable<ResponseBean> {


    private final InputData inputData;


    public HttpTask(InputData inputData) {

        this.inputData=inputData;
    }



    @Override
    public ResponseBean call() throws Exception {
        PaymentVaultClient paymentVaultClient = RestClientFactory.getInstance();
        try {
            //  System.out.println(paymentVaultClient.retrieveInstrument(paymentInstrument,UUID.randomUUID().toString()));
            // paymentVaultClient.retrieveInstrument(registeredInstrument.getPaymentInstrumentID(), UUID.randomUUID().toString());
            PaymentInstrument pi = paymentVaultClient.retrieveInstrument(inputData.getPaymentInstrument(), UUID.randomUUID().toString());
            return new ResponseBean(pi.getBrandName(),inputData);



        }
        catch (BaseClientException e) {
            //TODO handle exception for register
          //  throw new RuntimeException(e);
            return new ResponseBean("PIID not found",inputData);
        }
    }


}