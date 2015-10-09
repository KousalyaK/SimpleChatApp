package com.example.android.chatapp;

/**
 * Created by anjana on 10/9/15.
 */

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("User")

public class User extends ParseObject {
    public String getUsername(){
        return getString("username");
    }

    public void setUsername(String name){
        put("username", name);
    }
    public String getStatus(){
        return getString("status");
    }

    public void setStatus(String status){
        put("status", status);
    }
    @Override
    public String toString(){
        return getString("username") + "\n" + getString("status");
    }

}
