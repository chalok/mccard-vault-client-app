/**
 * Copyright 2015 Expedia, Inc. All rights reserved. EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.expedia.payment.vault.client.example.REST;


import com.expedia.e3.es.payment.common.rest.client.transport.HttpClientConfig;
import com.expedia.e3.es.payment.common.rest.client.transport.HttpClientTransport;
import com.expedia.e3.es.payment.common.rest.client.transport.ITransport;
import com.expedia.e3.es.payment.vault.client.PaymentVaultClient;

/**
 * @author Evan Harris
 */
public class RestClientFactory {

    private static PaymentVaultClient instance;

    public static synchronized PaymentVaultClient getInstance(){
        if(instance == null) {
            instance = createClient();
        }
        return instance;
    }

    private static PaymentVaultClient createClient(){
        
        HttpClientConfig httpClientConfig = new HttpClientConfig();

        
        ITransport transport = new HttpClientTransport(PaymentVaultClient.DEFAULT_KEYSTORE, httpClientConfig);
        return new PaymentVaultClient("https://paymentvault.karmalab.net",
                //This is a example drac ID only, submit ticket to DRAC team for credentials to Payment Vault
                "35f0d88f-5174-4c9d-8f73-19e6ecec2580",
                "8ed63d27553e9f3630478930da3dcced",
                "m1zx9L+Wz/9bjlLBm4e8aEsqk1K/+rSVAUlFkab5XUs=",
                transport);
    }
}
