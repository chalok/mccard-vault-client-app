package com.expedia.payment.vault.client.example.parser;

import com.expedia.payment.vault.client.example.model.InputData;

import java.io.*;
import java.util.ArrayList;

public class txtToArrayList {
    private File file;
    BufferedReader br;
    public txtToArrayList(File file){
        this.file = file;
        try {
            br = new BufferedReader(new FileReader(file));}
        catch (FileNotFoundException e){
            System.out.println(e);}
    }

    public ArrayList<InputData> extractAsList() {
        ArrayList<InputData> list = new ArrayList<InputData>();

        String line = null;

        try {
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                InputData singleRows = new InputData();
                  //  System.out.println(line);
                    singleRows.setExpuserid(Integer.parseInt(values[0]));
                    singleRows.setEmailAddress(values[1].trim());
                    singleRows.setTuidtobelooked(Integer.parseInt(values[3]));
                    singleRows.setTpidtobelooked(Integer.parseInt(values[4]));
                    singleRows.setCreditCardID(Integer.parseInt(values[5]));
                    singleRows.setPaymentInstrument(values[6]);
                    singleRows.setUpdatedate(values[7]);
                    singleRows.setDatabase(values[8]);
                    list.add(singleRows);

            }

        }catch (IOException e){
            throw new RuntimeException(e);}
        try{
        br.close();}
        catch (Exception e){
            throw new RuntimeException(e);
        }

    return list;

    }

}
