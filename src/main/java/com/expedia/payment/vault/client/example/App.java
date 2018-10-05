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
    PrintWriter writer,errorlogger,mc_record;
    public void registerCreditCardPaymentInstrument(){
        try{
            mc_record = new PrintWriter("mcrecord.txt", "UTF-8");
        writer = new PrintWriter("result.txt", "UTF-8");
        errorlogger=new PrintWriter("error.txt", "UTF-8");}
        catch(Exception e){
            throw new RuntimeException(e);
        }
      //  File excelFile = new File("/Users/calok/vault-client-example-app/mc_card_us_final2.txt"); // Change the location and file name as per yours
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("mc_card_us_final2.txt");
        InputStreamReader isr = new InputStreamReader(is);


        txtToArrayList uploaded = new txtToArrayList(isr);
        ArrayList<InputData> list = uploaded.extractAsList();

        System.out.println(list.size());

        publishMessage(list);


    }



    private String publishMessage(List<InputData> inputDataList) {
        final ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(75);
        final List<Future<ResponseBean>> futuresList = new ArrayList<>();

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

            processFutureList(futuresList);
        } catch (Exception e) {
            System.out.println("Exception occured while processing : " + e);
        }




        return "success";
    }

    private void processFutureList(List<Future<ResponseBean>> futuresList) throws InterruptedException, java.util.concurrent.ExecutionException {
        System.out.println(futuresList.size());

        for (final Future<ResponseBean> future : futuresList) {
            try {
                final ResponseBean res = future.get();
                  if (res.getBrandtype()!=null && res.getBrandtype().equalsIgnoreCase("MasterCard")) {
                      mc_record.write(res.getInputData().getEmailAddress() + "\t" + res.getInputData().getUpdatedate() + "\t" +
                              res.getInputData().getDatabase()+"\t"+res.getInputData().getTpidtobelooked()+"\n");
                      System.out.println(res.getInputData().getPaymentInstrument() + "\t" + res.getInputData().getDatabase() +
                              "\t" + res.getBrandtype());
                  }

                writer.write(res.getInputData().getExpuserid() +"\t"+res.getInputData().getTuidtobelooked()+ "\t" + res.getInputData().getPaymentInstrument() + "\t" + res.getInputData().getEmailAddress() + "\t" + res.getInputData().getUpdatedate() + "\t" + res.getInputData().getDatabase() +
                        "\t" + res.getBrandtype() +"\t"+res.getBin()+"\n");

            }catch(Exception e){
                System.out.println("exception :"+e);
                errorlogger.write(e+"\n");
            }
        }
        writer.close();
        mc_record.close();
        errorlogger.close();
        File f=new File("result.txt");
        System.out.println("file path "+f.getAbsolutePath());
        System.out.println(" Time taken = "+(endtime-starttime));
    }

    static long starttime,endtime;



    public static void main(String[] vargs) throws IOException {
        App app = new App();
        starttime=System.currentTimeMillis();
        app.registerCreditCardPaymentInstrument();


    }
}
