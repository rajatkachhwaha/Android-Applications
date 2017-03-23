package com.example.rjt.firebasedata;

/**
 * Created by Rjt on 3/21/2017.
 */

public class User {public String name;
    public String email;

    // Default constructor required for calls to
    // DataSnapshot.getValue(com.example.rjt.firebasedata.User.class)
    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
