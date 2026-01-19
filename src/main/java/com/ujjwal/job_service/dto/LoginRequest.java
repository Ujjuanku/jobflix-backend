package com.ujjwal.job_service.dto;

public class LoginRequest {
    private String username;
    private String password; // raw password from frontend

    // getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
