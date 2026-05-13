package com.example.contacts2026;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Contact implements Serializable {
    private String Avatar;
    private String Name;
    private String Email;
    private String ID;

    public Contact(String avatar, String name, String email) {
        Avatar = avatar;
        Name = name;
        Email = email;
        ID = String.valueOf(System.currentTimeMillis());
    }

    public Contact(String avatar, String name, String email,String id) {
        Avatar = avatar;
        Name = name;
        Email = email;
        ID = id;
    }

    public String getAvatar() {
        return Avatar;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getID() { return ID;}

    public Map<String,String> getAsMap() {
        Map<String,String> map = new HashMap<>();
        map.put("ID",ID);
        map.put("Avatar",Avatar);
        map.put("Name",Name);
        map.put("Email", Email);
        return map;
    }
}
