package com.example.loadersapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public final class QuakesUtiles {
    private static final String TAG = QuakesUtiles.class.getSimpleName();

    private QuakesUtiles() {
    }

    ;

    public static ArrayList<DataContainerModel> fetchDataBackToQuakesLoader(String urlQuakesApi) {

        //because of this file have been excuted in the doInBackground so we can do all the network stuff in it ...
        URL url = GenerateUrl(urlQuakesApi);
        //second part of this function is response
        String jsonResponse = "";
        try {
            jsonResponse = MakeHttpRequest(url);
        } catch (IOException e) {
            //handled later
        }
        ;
        ArrayList<DataContainerModel> FetchedCompleteData = ExtractDataFromJsonObject(jsonResponse);
        return FetchedCompleteData;
    }

    ;

    private static String MakeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        ;
        HttpURLConnection URLConnection = null;
        InputStream inputstream = null;
        try {
            URLConnection = (HttpURLConnection) url.openConnection();
            URLConnection.setRequestMethod("GET");
            URLConnection.setReadTimeout(10000/*Milliseconds*/);
            URLConnection.setConnectTimeout(15000/*Milliseconds*/);
            URLConnection.connect();
            //check if everything  is ok ? or not
            if (URLConnection.getResponseCode() == 200 || URLConnection.getResponseCode() == URLConnection.HTTP_OK) {

                inputstream = URLConnection.getInputStream();
                jsonResponse = ReadInputStream(inputstream);

            }
            ;
        } catch (IOException e) {
            //handled later
        } finally {
            if (URLConnection != null) {
                URLConnection.disconnect();

            }
            ;
            if (inputstream != null) {
                inputstream.close();
            }
            ;
        }
        ;
        return jsonResponse;
    }

    ;

    private static String ReadInputStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader ReadInputStream = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader Read = new BufferedReader(ReadInputStream);
            String Line = Read.readLine();
            while (Line != null) {
                output.append(Line);
                Line = Read.readLine();
            }
            ;
        }
        ;

        return output.toString();
    }

    ;

    private static URL GenerateUrl(String urlQuakesApi) {
        URL url = null;
        try {
            url = new URL(urlQuakesApi);
        } catch (MalformedURLException e) {
            Log.e(TAG, "error with the url that u entered", e);
        }
        ;
        return url;
    }

    ;

    private static ArrayList<DataContainerModel> ExtractDataFromJsonObject(String jsonResponse) {

        ArrayList<DataContainerModel> arr = new ArrayList<>();
        try {
            JSONObject Root = new JSONObject(jsonResponse);
            JSONArray innerRootArray = Root.getJSONArray("features");
            for (int i = 0; i < innerRootArray.length(); i++) {
                JSONObject InnerObject = innerRootArray.getJSONObject(i);
                JSONObject properties = InnerObject.getJSONObject("properties");

                //fetch the data now
                String mag = properties.getString("mag");
                String place = properties.getString("place");
                String url = properties.getString("url");
                long Time = properties.getLong("time");

                arr.add(new DataContainerModel(mag, place, url, Time));

            }
            ;
        } catch (JSONException e) {
            //handled later

        }
        ;
        return arr;
    }

    ;
};
