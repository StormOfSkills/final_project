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
        String phpPageULR="http://192.168.8.108:8080/android/mysql_write.php";
        try {
            // preparing the URL for the connection
            URL url=new URL(phpPageULR);
            // open a channel between the client(android device) and the server (PHP)
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            // specify what do you need post or get method
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            // create a sub-channel inside the channel to specify weither you want to write or read
            // output means write, input means read
            OutputStream subChannel=channel.getOutputStream();
            // create a pen to write in a specific information and in which language should this pen write.
            OutputStreamWriter pen=new OutputStreamWriter(subChannel, StandardCharsets.UTF_8);
            // create a object to start write the information
            BufferedWriter student =new BufferedWriter(pen);
            // information to write
            String name = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String phone = URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
            String email = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            String information=name+"&"+phone+"&"+email;
            // student will start writing the information
            student.write(information);
            // student will push the information from the client side to the server side
            student.flush();
            // student finished his job
            student.close();
            System.out.println(params[1]);
            // closing the sub-channel
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
        String phpPageULR="http://192.168.8.108:8080/android/deletedata.php";
        try {
            // preparing the URL for the connection
            URL url=new URL(phpPageULR);
            // open a channel between the client(android device) and the server (PHP)
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            // specify what do you need post or get method
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            // create a sub-channel inside the channel to specify weither you want to write or read
            // output means write, input means read
            OutputStream subChannel=channel.getOutputStream();
            // create a pen to write in a specific information and in which language should this pen write.
            OutputStreamWriter pen=new OutputStreamWriter(subChannel, StandardCharsets.UTF_8);
            // create a object to start write the information
            BufferedWriter student =new BufferedWriter(pen);
            // information to write
            String userid = URLEncoder.encode("userId", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String information=userid;
            // student will start writing the information
            student.write(information);
            // student will push the information from the client side to the server side
            student.flush();
            // student finished his job
            student.close();
            System.out.println(params[1]);
            // closing the sub-channel
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
        String phpPageULR="http://192.168.8.108:8080/android/updatedata.php";
        try {
            // preparing the URL for the connection
            URL url=new URL(phpPageULR);
            // open a channel between the client(android device) and the server (PHP)
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            // specify what do you need post or get method
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);
            // create a sub-channel inside the channel to specify weither you want to write or read
            // output means write, input means read
            OutputStream subChannel=channel.getOutputStream();
            // create a pen to write in a specific information and in which language should this pen write.
            OutputStreamWriter pen=new OutputStreamWriter(subChannel, StandardCharsets.UTF_8);
            // create a object to start write the information
            BufferedWriter student =new BufferedWriter(pen);
            // information to write
            String userid = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String name = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
            String phone = URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8");
            String email = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8");

            String information=userid+"&"+name+"&"+phone+"&"+email;
            // student will start writing the information
            student.write(information);
            // student will push the information from the client side to the server side
            student.flush();
            // student finished his job
            student.close();
            System.out.println(params[1]);
            // closing the sub-channel
            subChannel.close();
            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Uploading Data into PHP MySQL";
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
