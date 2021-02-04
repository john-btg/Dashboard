package com.example.demo.Google;

import com.squareup.okhttp.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Youtube {

    private String Token;
    private Object JSONObject;

    public void recupToken(String code) throws JSONException, IOException {
        OkHttpClient client = new OkHttpClient();
        JSONObject js=new JSONObject();
        js.put("code", code);
        js.put("client_id","263403587859-2hjchtviipii41q94u0n0hb429dkvokr.apps.googleusercontent.com");
        js.put("client_secret","kcmoM8y_afiLuR2_gSIxU_FR");
        js.put("redirect_uri","http://localhost:8080/google/youtube");
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




    public void Widget(String chaine) throws IOException, JSONException {



        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=" + chaine + "&key=AIzaSyDTEByrKhznEoLi9d98sqxz0MYe_xbu9bA")
                .addHeader("Authorization", "Bearer " + this.Token)
                .build();

        Response response = client.newCall(request).execute();
         String resStr=response.body().string();
        System.out.println(resStr);
        JSONObject Jobject = new JSONObject(resStr);
        System.out.println(Jobject.names());

        String rendu="";
        JSONArray JoArr= (JSONArray) Jobject.get("items");
        System.out.println(JoArr.length());
        System.out.println("Youtubeur : "+JoArr.getJSONObject(0).getJSONObject("snippet").getString("title")
                            +"\nDescription : "+JoArr.getJSONObject(0).getJSONObject("snippet").getString("description")
                            +"\nCount of view : "+JoArr.getJSONObject(0).getJSONObject("statistics").getString("viewCount")
                            +"\nCount of subscriber : "+JoArr.getJSONObject(0).getJSONObject("statistics").getString("subscriberCount")
                            +"\nCount of Video online : "+JoArr.getJSONObject(0).getJSONObject("statistics").getString("videoCount")
        );



//        String rendu =title+"\n"+descr+"\n"+viewCounts+"\n"+subscriberCounts+"\n"+videoCounts;
//        int index = Jobject.getString("items").indexOf(',', Jobject.getString("items").indexOf("viewCount"));
//
//        String reponseCoupe = "";
//        for (int i = Jobject.getString("items").indexOf("viewCount") + 12; i < index - 1; i++) {
//            reponseCoupe += Jobject.getString("items").charAt(i);
//        }


    }
}
