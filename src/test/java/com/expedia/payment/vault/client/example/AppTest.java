package com.expedia.payment.vault.client.example;

import org.junit.Test;

public class AppTest {

    private App app = new App();

    @Test
    public void testRegisterCardPaymentInstrument(){
        app.registerCreditCardPaymentInstrument();
    }

    @Test
    public void testRegisterPayPalPaymentInstrument(){
        app.registerPayPalPaymentInstrument();
    }

    @Test
    public void testRegisterPointsPaymentInstrument(){
        app.registerPointsPaymentInstrument();
    }

    @Test
    public void testRegisterDirectDebitPaymentInstrument(){
        app.registerDirectDebitPaymentInstrument();
    }

    @Test
    public void testRegisterGiftCardPaymentInstrument(){
        app.registerGiftCardPaymentInstrument();
    }
}