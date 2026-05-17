package com.expensetracker.backend.dto;

public class LoginResponseDTO {

    private final String token;
    private final String tokenType = "Bearer";
    private final Long userId;
    private final String benutzername;
    private final String email;
    private final String role;
    private final long expiresIn;

    public LoginResponseDTO(String token, Long userId, String benutzername, String email, String role, long expiresIn) {
        this.token = token;
        this.userId = userId;
        this.benutzername = benutzername;
        this.email = email;
        this.role = role;
        this.expiresIn = expiresIn;
    }
    public String getToken() {
        return token;
    }
    public String getTokenType() {
        return tokenType;
    }
    public Long getUserId() {
        return userId;
    }
    public String getBenutzername() {
        return benutzername;
    }
    public String getEmail() {
        return email;
    }
    public String getRole() {
        return role;
    }
    public long getExpiresIn() {
        return expiresIn;
    }

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "token ='[HIDDEN]'" +
                ", tokenType='" + tokenType + '\'' +
                ", benutzername='" + benutzername + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", expiresIn=" + expiresIn +
                "}";
    }

}
