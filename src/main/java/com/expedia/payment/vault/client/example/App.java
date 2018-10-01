/**
 * Copyright 2015 Expedia, Inc. All rights reserved. EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.expedia.payment.vault.client.example;


import com.expedia.payment.vault.client.example.REST.HttpTask;
import com.expedia.payment.vault.client.example.model.InputData;
import com.expedia.payment.vault.client.example.model.ResponseBean;
import com.expedia.payment.vault.client.example.parser.txtToArrayList;

import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Evan Harris
 */
public class App {
    PrintWriter writer ;
    public void registerCreditCardPaymentInstrument(){
        try{
        writer = new PrintWriter("result.txt", "UTF-8");}
        catch(Exception e){
            throw new RuntimeException(e);
        }
        File excelFile = new File("/Users/calok/vault-client-example-app/src/main/java/com/expedia/payment/vault/client/example/tfinaltableint.txt"); // Change the location and file name as per yours
        txtToArrayList uploaded = new txtToArrayList(excelFile);
        ArrayList<InputData> list = uploaded.extractAsList(); // Rows in excel will be returned as list
       //for(InputData lst:list){
         //  System.out.println(lst.getExpuserid()+" "+lst.getTuidtobelooked()+" "+lst.getTpidtobelooked()+" "+lst.getPaymentInstrument()+" "+lst.getUpdatedate());
       // }
        System.out.println(list.size());

        publishMessage(list);


    }



    private String publishMessage(List<InputData> inputDataList) {
        final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);
        final List<Future<ResponseBean>> futuresList = new ArrayList<>();
      //  final LocalDate localDate = LocalDate.now(Clock.system(ZoneId.of("America/Los_Angeles")));
        try {
            for (final InputData inputDatalst : inputDataList) {
                final HttpTask httpTask = new HttpTask(inputDatalst);
                final Future<ResponseBean> futures = threadPoolExecutor.submit(httpTask);
                futuresList.add(futures);
            }
            threadPoolExecutor.shutdown();
            while (!threadPoolExecutor.isTerminated()) {
            }
            endtime=System.currentTimeMillis();
            System.out.println(" Time take= "+(endtime-starttime));
            processFutureList(futuresList);
        } catch (Exception e) {
            System.out.println("Exception occured while processing : " + e);
        }




        return "success";
    }

    private void processFutureList(List<Future<ResponseBean>> futuresList) throws InterruptedException, java.util.concurrent.ExecutionException {
        System.out.println(futuresList.size());
        int i = 1;
        for (final Future<ResponseBean> future : futuresList) {

            final ResponseBean res = future.get();
         //   if (res.getBrandtype()!=null && res.getBrandtype().equalsIgnoreCase("MasterCard")) {
                writer.write(res.getInputData().getExpuserid()+"\t"+res.getInputData().getPaymentInstrument()+"\t"+res.getInputData().getEmailAddress()+"\t"+res.getInputData().getUpdatedate()+"\t"+res.getInputData().getDatabase()+
                        "\t"+res.getBrandtype()+"\n");
                System.out.println(res.getInputData().getExpuserid()+"\t"+res.getInputData().getPaymentInstrument()+"\t"+res.getInputData().getEmailAddress()+"\t"+res.getInputData().getUpdatedate()+"\t"+res.getInputData().getDatabase()+
                        "\t"+res.getBrandtype());
                i++;
          //  }
        }
        writer.close();
        System.out.println("i: "+i);
    }

    static long starttime,endtime;



    public static void main(String[] vargs) throws IOException {
        App app = new App();
        starttime=System.currentTimeMillis();
        app.registerCreditCardPaymentInstrument();


    }
}
