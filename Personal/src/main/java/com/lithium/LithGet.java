package com.lithium;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LithGet {

    public static void main(String[] args) {
        System.out.println(
                getPages(getRequest("https://jsonmock.hackerrank.com/api/movies/search/?Title=" + "spiDERMan")));
    }

    static String getRequest(String urlString) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) response.append(inputLine);
                in.close();
                System.out.println(response.toString());
            } else System.out.println("Failed");
        } catch (Exception e) {
        }
        return response.toString();

    }

    static int getPages(String json) {
        int pages = 0;
        String[] tokens = json.split(",");
        for (String token : tokens) {
            if (token.contains("total_pages")) {
                try {
                    pages = Integer.parseInt(token.split(":")[1]);
                } catch (NumberFormatException e) {

                }
                break;
            }
        }
        return pages;
    }
}
