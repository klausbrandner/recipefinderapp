package com.a5_designs.recipefinder;

import android.os.AsyncTask;
import android.util.Log;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class RecipeHttpPostService extends AsyncTask<String, Void, String> {

    String data;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    public RecipeHttpPostService(String data) {
        this.data = data;
    }

    @Override
    protected String doInBackground(String... urls) {
        String url = urls[0];

        try {
            URL obj = new URL(url);
            Log.d("POSTURL",url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");


            // Send post request
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Boolean result) {

    }
}