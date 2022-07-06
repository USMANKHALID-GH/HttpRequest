package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class Main {
    private static HttpURLConnection connection;

    public static void main(String[] args) {
        BufferedReader bufferedReader;
        String line;
        StringBuffer stringBuffer=new StringBuffer();

        // method one using java.net.http
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/albums");
            connection= (HttpURLConnection) url.openConnection();

//            request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(500);
            int value= connection.getResponseCode();
            if(value>299){
                bufferedReader=new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line=bufferedReader.readLine())!=null){
                    stringBuffer.append(line);
                }
            }
            else
                bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line=bufferedReader.readLine())!=null){
                System.out.println(line);
                stringBuffer.append(line);
            }
            System.out.println(stringBuffer);
        } catch (Exception e) {

        }
        finally {
            connection.disconnect();
        }

    }
}