package com.example.user.inventoryandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

public class BackgroundTask extends AsyncTask<String,Void,String> {

    private Context context;
    private AlertDialog alertDialog;
    private String user_email;
    private String method;

    private String data;

    private Exporter exporter;

    public BackgroundTask(Context context, Exporter exporter) {

        this.context = context;
        this.exporter=exporter;
    }

    public BackgroundTask(Context context) {

        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Info.");
    }

    @Override
    protected String doInBackground(String... params) {



        String reg_url = "https://elewartors.000webhostapp.com/register_user.php";
        String log_url = "https://elewartors.000webhostapp.com/login.php";
        String json_get_mData_url = "https://elewartors.000webhostapp.com/json_get_mdata.php";
        String post_url = "https://elewartors.000webhostapp.com/post_data.php";
        String delete_item_url = "https://elewartors.000webhostapp.com/delete_data_item.php";

        method = params[0];

// Register method
        if (method.equals("register")){
            String email = params[1];
            String password = params[2];

            try {

                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String data =
                        URLEncoder.encode("email","UTF-8") +  "=" + URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("pass","UTF-8") +  "=" + URLEncoder.encode(password,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                return "Your account was created success";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

// Login method
        }else if(method.equals("login")){

            String email = params[1];
            user_email = email;
            String password = params[2];

            try {

                URL url = new URL(log_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data =
                        URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("pass", "UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null){
                    response+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(method.equals("get_mData")){
            try {
                URL url = new URL(json_get_mData_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String tempData;
                while ((tempData = bufferedReader.readLine()) != null){

                    stringBuilder.append(tempData+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if (method.equals("post new data item")){

            String name = params[1];
            String description= params[2];
            String status= params[3];
            String res_person= params[4];
            String price= params[5];
            String buy_date= params[6];
            String dep_time= params[7];
            String location= params[8];
            String category= params[9];
            String image= params[10];
            String comment= params[11];

            try {

                URL url = new URL(post_url);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String data =
                        URLEncoder.encode("name","UTF-8") +  "=" + URLEncoder.encode(name,"UTF-8")+"&"+
                                URLEncoder.encode("description","UTF-8") +  "=" + URLEncoder.encode(description,"UTF-8")+"&"+
                                URLEncoder.encode("status","UTF-8") +  "=" + URLEncoder.encode(status,"UTF-8")+"&"+
                                URLEncoder.encode("res_person","UTF-8") +  "=" + URLEncoder.encode(res_person,"UTF-8")+"&"+
                                URLEncoder.encode("price","UTF-8") +  "=" + URLEncoder.encode(price,"UTF-8")+"&"+
                                URLEncoder.encode("buy_date","UTF-8") +  "=" + URLEncoder.encode(buy_date,"UTF-8")+"&"+
                                URLEncoder.encode("dep_time","UTF-8") +  "=" + URLEncoder.encode(dep_time,"UTF-8")+"&"+
                                URLEncoder.encode("location","UTF-8") +  "=" + URLEncoder.encode(location,"UTF-8")+"&"+
                                URLEncoder.encode("category","UTF-8") +  "=" + URLEncoder.encode(category,"UTF-8")+"&"+
                                URLEncoder.encode("image","UTF-8") +  "=" + URLEncoder.encode(image,"UTF-8")+"&"+
                                URLEncoder.encode("comment","UTF-8") +  "=" + URLEncoder.encode(comment,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                return "Item was added success";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else if (method.equals("delete data item")) {

            String id = params[1];

            try {

                URL url = new URL(delete_item_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                return "Item was deleted success";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {

        if (method.equals("login")){
            if (result.equals("Login with "+user_email+" is success")){
                Intent intent = new Intent(context, InventoryBranchActivity.class);
                context.startActivity(intent);
            }else{
                alertDialog.setMessage(result);
                alertDialog.show();
            }
        }else if(method.equals("register")) {
            if (result.equals("Your account was created success")) {
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            }
        }else if (method.equals("get_mData")) {
            exporter.sendData(result);
        }else if (method.equals("post new data item")){
            if(result.equals("Item was added success"))
                Toast.makeText(context, "Item was added success", Toast.LENGTH_SHORT).show();
        }else if (method.equals("delete data item")){
            if(result.equals("Item was deleted success")){
                Toast.makeText(context, "Item was deleted success", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
