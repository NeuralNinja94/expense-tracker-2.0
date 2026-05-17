package com.expensetracker.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequestDTO {
    @NotBlank(message = "Benutzername ist erforderlich")
    @Size(min = 3, max = 50, message = "Benutzername muss zwischen 3 und 50 zeichen haben")
    private String benutzername;

    @NotBlank(message = "Email ist erforderlich")
    @Email(message = "Email muss ein gültiges Format haben")
    @Size(max = 50, message = "Email darf maximal 50 Zeichen haben")
    private String email;

    @NotBlank(message = "Passwort ist erforderlich")
    @Size(min = 8, message ="Passwort muss mindestens 8 Zeichen haben")
    private String passwort;

    public RegisterRequestDTO() {}

    public RegisterRequestDTO(String benutzername, String email, String passwort) {
        this.benutzername = benutzername;
        this.email = email;
        this.passwort = passwort;
    }
    public String getBenutzername() {
        return benutzername;
    }
    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPasswort() {
        return passwort;
    }
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
    @Override
    public String toString() {
        return "RegisterRequestDTO{" +
                "benutzername=" + benutzername + '\'' +
                ", email='" + email + '\'' +
                ", password='[HIDDEN]'" +
                '}';
    }

}
