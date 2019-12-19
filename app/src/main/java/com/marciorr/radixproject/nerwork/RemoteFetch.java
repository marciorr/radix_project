package com.marciorr.radixproject.nerwork;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RemoteFetch {

    //Indica a URL base da API
    private static final String GITHUB_API =
            "https://api.github.com/users/%s";

    //Acessa a URL do Usuário e retorna o objeto JSON
    public static JSONObject getJSON(Context context, String usuario){
        try {
            URL url = new URL(String.format(GITHUB_API, usuario));
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            return data;
        }catch(Exception e){
            return null;
        }
    }
}