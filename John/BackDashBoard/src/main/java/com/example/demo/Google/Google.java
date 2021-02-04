package com.example.demo.Google;

import com.google.api.client.json.Json;
import com.squareup.okhttp.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Google {


    private Object JSONObject;
    private String Token;
    public void recupToken(String code) throws JSONException, IOException {
        OkHttpClient client = new OkHttpClient();
        JSONObject js=new JSONObject();
        js.put("code", code);
        js.put("client_id","263403587859-2hjchtviipii41q94u0n0hb429dkvokr.apps.googleusercontent.com");
        js.put("client_secret","kcmoM8y_afiLuR2_gSIxU_FR");
        js.put("redirect_uri","http://localhost:8080/google/accessToken");
        js.put("grant_type","authorization_code");

        String jsontString=js.toString();
        RequestBody body=RequestBody.create((MediaType) JSONObject,jsontString);
        Request request = new Request.Builder()
                    .url("https://oauth2.googleapis.com/token")
                    .post(body)
                    .build();
        Response response=client.newCall(request).execute();


        String respToString=response.body().string();
        JSONObject jsonRes=new JSONObject(respToString);
        this.Token=jsonRes.getString("access_token");
        System.out.println(this.Token);
    }
}

