package com.example.mydam;
public class user {
    public String fullname,username,email,gender,spin;
    public user()
    {

    }

    public String getSpin() {
        return spin;
    }

    public void setSpin(String spin) {
        this.spin = spin;
    }

    public user(String fullname, String username, String email, String gender, String spin) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.spin=spin;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
