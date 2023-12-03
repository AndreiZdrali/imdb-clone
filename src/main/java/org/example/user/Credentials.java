package org.example.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Credentials {
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    public Credentials(@JsonProperty("email") String email,
                       @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }

    //TODO: Use encapsulation????
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "Email: " + email + "\n" +
                "Password: " + password + "\n";
    }
}
