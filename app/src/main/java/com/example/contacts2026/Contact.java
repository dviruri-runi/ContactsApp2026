package com.example.contacts2026;

import java.io.Serializable;

public class Contact implements Serializable {
    private String Avatar;
    private String Name;
    private String Email;

    public Contact(String avatar, String name, String email) {
        Avatar = avatar;
        Name = name;
        Email = email;
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
}
