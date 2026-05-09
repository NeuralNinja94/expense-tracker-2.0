package com.expensetracker.backend.entities;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;




@Entity
@Table(name ="app_user")
public class AppUser implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;


    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50, unique = true)
    private String benutzername;

    @Column(nullable = false)
    private String passwort;

    @OneToMany(mappedBy = "appUser")
    private List<Expense> expenses;

    private LocalDate erstellungsdatum;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    //Konstruktor

    protected AppUser() {}

    public AppUser(String email, String benutzername, String passwort, Role role) {
        this.email = email;
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.role = role;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String getPassword() {
        return passwort;
    }
    @Override
    public String getUsername() {
        return email;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }
    public void setVersion(Long version) {
        this.version = version;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBenutzername() {
        return benutzername;
    }
    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }
    public String getPasswort() {
        return passwort;
    }
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
    public LocalDate getErstellungsdatum() {
        return erstellungsdatum;
    }
    public void setErstellungsdatum(LocalDate erstellungsdatum) {
        this.erstellungsdatum = erstellungsdatum;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }




}

  
