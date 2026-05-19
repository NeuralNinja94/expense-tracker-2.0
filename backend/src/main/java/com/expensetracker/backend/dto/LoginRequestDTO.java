package com.expensetracker.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    @NotBlank (message = "Benutzername oder Email darf nicht leer sein")
    private String benutzernameOrEmail;

    @NotBlank (message = "Passwort ist erforderlich")
    private String passwort;

    public LoginRequestDTO() {}

    public LoginRequestDTO(String benutzernameOrEmail, String passwort) {
        this.benutzernameOrEmail = benutzernameOrEmail;
        this.passwort = passwort;
    }


    public String getBenutzernameOrEmail() {
        return benutzernameOrEmail;
    }
    public void setBenutzernameOrEmail(String benutzernameOrEmail) {
        this.benutzernameOrEmail = benutzernameOrEmail;
    }

    public String getPasswort() {
        return passwort;
    }
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    @Override
    public String toString() {
        return "LoginRequestDTO{" +
                "benutzernameOrEmail='" + benutzernameOrEmail + '\'' +
                ", passwort='[HIDDEN]'" +
                    '}';
    }
}
