package com.expensetracker.backend.services;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expensetracker.backend.entities.AppUser;
import com.expensetracker.backend.repositories.AppUserRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AppUserService {

//Dependencies via Constructor Injection
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Alle Benutzer abrufen
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
    //Benutzer nach ID abrufen
    public AppUser getUserById(@NonNull Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden mit der ID: " + id));
    }
    //Neuen Benutzer erstellen
    public AppUser createUser(@NonNull AppUser appUser) {
        return userRepository.save(appUser);
    }

    //Benutzer löschen
    public void deleteUser(@NonNull Long id) {
        userRepository.deleteById(id);
    }
    //Anmeldeinformationen validieren
    public void validateCredentials(@NonNull String benutzername, @NonNull String passwort){
        // Validierung der Anmeldeinformationen
        if (benutzername.isBlank()) {
            throw new IllegalArgumentException("Bitte Benutzername eingeben");
            }
        if (passwort.isBlank()) {
            throw new IllegalArgumentException("Bitte Passwort eingeben");
        }
    }
    
    //Benutzer aktualisieren
    public AppUser updateUser(@NonNull Long id, @NonNull AppUser updatedAppUser) {
        AppUser existingAppUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden mit der ID: " + id));

        existingAppUser.setBenutzername(updatedAppUser.getBenutzername());
        existingAppUser.setPasswort(updatedAppUser.getPasswort());
        

        return userRepository.save(existingAppUser);
    }
    //Passwort hashing


    

    

}
