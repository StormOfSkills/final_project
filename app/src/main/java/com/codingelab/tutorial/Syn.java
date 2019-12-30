package com.codingelab.tutorial;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class Syn extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String ... params) {
        if(params.length>0) {
            if (params[0].equalsIgnoreCase("syn")) {
                onSyn();
            } else if (params[0].equalsIgnoreCase("insert")) {
                return onInsert(params);
            } else if (params[0].equalsIgnoreCase("delete")) {
                return onDelete(params);
            } else if (params[0].equalsIgnoreCase("update")) {
                return onUpdate(params);
            }
        }
        return null;
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private String onInsert(String ... params){
        String phpPageULR="http://192.168.43.8:8080/sqli/mysql_write.php";
        try {
            URL url=new URL(phpPageULR);
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            OutputStream subChannel=channel.getOutputStream();
            OutputStreamWriter pen=new OutputStreamWriter(subChannel, StandardCharsets.UTF_8);
            BufferedWriter student =new BufferedWriter(pen);
            String name = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String phone = URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
            String email = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            String information=name+"&"+phone+"&"+email;
            student.write(information);
            student.flush();
            student.close();
            System.out.println(params[1]);
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Data Added To PHP MySQL";
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private String onDelete(String ... params){
        String phpPageULR="http://192.168.43.8:8080/sqli/deletedata.php";
        try {
            URL url=new URL(phpPageULR);
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            OutputStream subChannel=channel.getOutputStream();
            OutputStreamWriter pen=new OutputStreamWriter(subChannel, StandardCharsets.UTF_8);
            BufferedWriter student =new BufferedWriter(pen);
            String userid = URLEncoder.encode("userId", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String information=userid;
            student.write(information);
            student.flush();
            student.close();
            System.out.println(params[1]);
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Data Deleted From PHP MySQL";
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private String onUpdate(String ... params){
        String phpPageULR="http://192.168.43.8:8080/sqli/updatedata.php";
        try {
            URL url=new URL(phpPageULR);
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            OutputStream subChannel=channel.getOutputStream();
            OutputStreamWriter pen=new OutputStreamWriter(subChannel, StandardCharsets.UTF_8);
            BufferedWriter student =new BufferedWriter(pen);
            String userid = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String name = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
            String phone = URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            String email = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8");
            String information=userid+"&"+name+"&"+phone+"&"+email;
            student.write(information);
            student.flush();
            student.close();
            System.out.println(params[1]);
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Updating Data into PHP MySQL";
    }

    private void onSyn(){
        System.out.println(" Inserting Data");
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
