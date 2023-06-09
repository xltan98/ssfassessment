package vttp2023.batch3.ssf.frontcontroller.model;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Authenticate {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Authenticate create(String jsonstr){
        JsonReader r= Json.createReader(new StringReader(jsonstr));
        JsonObject o = r.readObject();
        
        Authenticate auth= new Authenticate();
        auth.setMessage(o.getString("message"));
        
        return auth;
        
    }

    

    


    
}
