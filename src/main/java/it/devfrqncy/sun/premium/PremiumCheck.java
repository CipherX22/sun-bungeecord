package it.devfrqncy.sun.premium;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PremiumCheck {
    final private String name;
    private String uuid;
    private boolean result;

    public PremiumCheck(String name,String uuid){
        this.name = name;
        this.uuid = uuid;
        this.result = false;
        check();
    }

    public PremiumCheck(String name){
        this.name = name;
        this.result = false;
        check();
    }

    private void check(){
        try {
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/"+this.name);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            String jsonstr  = reader.readLine();
            JsonElement jsonElement = new JsonParser().parse(jsonstr);
            JsonObject json = jsonElement.getAsJsonObject();
            //System.out.println("PREMIUM\n"+name+" - "+json.get("name").toString());
            this.result = name.equals(json.get("name").toString().replace("\"",""));
        } catch (Exception e) {
            this.result = false;
            //e.printStackTrace();
        }
    }

    public boolean getResult() { return result; }
}
