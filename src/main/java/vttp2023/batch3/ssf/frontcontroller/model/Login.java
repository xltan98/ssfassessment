package vttp2023.batch3.ssf.frontcontroller.model;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.Size;

public class Login {
    @Size(min=2, message="Username must be at least 2 characters in length")
    private String username;

    @Size(min=2, message="Password must be at least 2 characters in length")
    private String password;

    private int captchaAnswer;
    

    

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static JsonObject toJson(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        return jsonObject;
    }

    public static Login create (String jsonstr){
        Login login = new Login();
        JsonObject o = toJson(jsonstr);
        login.setPassword(o.getString("password"));
        login.setUsername(o.getString("username"));

        return login;
    }
     
    public JsonObject toJson(){
        return Json.createObjectBuilder()
        .add("username",this.username)
        .add("password",this.password)
        .build();
    }
    public int getCaptchaAnswer() {
        return captchaAnswer;
    }
    public void setCaptchaAnswer(int captchaAnswer) {
        this.captchaAnswer = captchaAnswer;
    }
   
    




    
    
}
