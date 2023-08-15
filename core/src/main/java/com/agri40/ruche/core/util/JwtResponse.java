package com.agri40.ruche.core.util;

public class JwtResponse {

    private String username;
    private String email;

    private String jwtToken;

    public JwtResponse() {}

    public JwtResponse(String username, String email, String jwtToken) {
        this.username = username;
        this.email = email;
        this.jwtToken = jwtToken;
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

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
