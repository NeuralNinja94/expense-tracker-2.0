package com.expensetracker.backend.entities;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Entity
public class Expense {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titel;

    @NotNull
    @DecimalMin("0.1")
    private Double betrag;

    @NotBlank
    private String kategorie;

    //Ausgabendatum
    @NotNull
    private LocalDate datum;
    
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }
    public void setTitel(String titel) {
        this.titel = titel;
    }
    public Double getBetrag() {
        return betrag;
    }
    public void setBetrag(Double betrag) {
        this.betrag = betrag;
    }
    public String getKategorie() {
        return kategorie;
    }
    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }
    public LocalDate getDatum() {
        return datum;
    }
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public AppUser getUser() {
        return appUser;
    }

    public void setUser(AppUser appUser) {
        this.appUser = appUser;
    }

    
}
