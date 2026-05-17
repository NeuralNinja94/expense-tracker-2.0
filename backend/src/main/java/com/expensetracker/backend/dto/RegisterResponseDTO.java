package com.expensetracker.backend.dto;

public class RegisterResponseDTO {

    private final Long userId;
    private final String benutzername;
    private final String email;
    private final String role;
    private final String message;

    public RegisterResponseDTO(Long userId, String benutzername, String email, String role) {
        this.userId = userId;
        this.benutzername = benutzername;
        this.email = email;
        this.role = role;
        this.message = "Registrierung erfolgreich!"
                + benutzername + "!";
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
    public String getMessage() {
        return message;
    }
    @Override
    public String toString() {
        return "RegisterResponseDTO{" +
                "id=" + userId +
                ", benutzername='" + benutzername + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", message'" + message + '\'' +
                '}';
    }
}
