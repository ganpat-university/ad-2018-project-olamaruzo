package com.example.olamaruzo;

public class UserDataModel {
    String email;
    String password;
    UserDataModel(String email,String password)
    {
        this.email=email;
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
