package it.devfrqncy.sun.IPapi;



import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

public class IPapi {
    private String url;
    private String ip;
    private String fields;
    private JsonObject user;

    public IPapi(String ip){
        this.ip = ip;
        this.url = "http://ip-api.com/json/";
        this.fields = "?fields=status,message,continent,country,countryCode,region,regionName,city,isp,org,as,asname,reverse,mobile,proxy,hosting,query";
        this.user = null;
        curl();
    }

    private void curl(){
        try {
            java.net.URL url = new URL(this.url+this.ip+this.fields);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String jsonstr  = reader.readLine();
            JsonParser jsonParser = new JsonParser();
            this.user = (JsonObject)jsonParser.parse(jsonstr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JsonObject getUser(){ return this.user; }

}