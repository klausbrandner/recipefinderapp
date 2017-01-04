package com.a5_designs.recipefinder;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

class RecipeHttpGetService extends AsyncTask<String, Void, String> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... urls) {
        String url = urls[0];

        try {
            InputStream inStream = new java.net.URL(url).openStream();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(inStream));
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