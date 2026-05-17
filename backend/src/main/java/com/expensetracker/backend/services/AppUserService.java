package com.expensetracker.backend.services;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import com.expensetracker.backend.entities.Role;
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

    //Neuen User registrieren
    public AppUser registerUser(String benutzername, String email,
                                String rawPassword, Role role){

        if (userRepository.existsByBenutzername(benutzername)) {
            throw new IllegalArgumentException("Benutzername bereits vergeben");
        }
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email bereits registriert");
        }

        if (benutzername == null || benutzername.isBlank()){
            throw new IllegalArgumentException("Benutzername darf nicht leer sein!");
        }
        if (email == null || email.isBlank()){
            throw new IllegalArgumentException("Email darf nicht leer sein!");
        }
        if (rawPassword == null || rawPassword.isBlank()){
            throw new IllegalArgumentException("Password darf nicht leer sein!");
        }

        String hashed = passwordEncoder.encode(rawPassword);

        Role effectiveRole = (role != null) ? role : Role.USER;
        AppUser user = new AppUser(benutzername, email, hashed, effectiveRole);

        return userRepository.save(user);
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

    //Benutzer nach Email finden
    public Optional<AppUser> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    //Nach Benutzername finden
    public Optional<AppUser> findByBenutzername(String benutzername){
        return userRepository.findByBenutzername(benutzername);
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
    //Authentifikation eines User (Vorbereitung für Login
    public Optional<AppUser> authenticateUser(String benutzername, String rawPasswort) {
        //User suchen
        Optional<AppUser> userOpt = userRepository.findByBenutzername(benutzername);

        if (userOpt.isPresent()) {
            AppUser appUser = userOpt.get();

            //Password prüfen mit BCrypt
            if (passwordEncoder.matches(rawPasswort, appUser.getPasswort())) {

                return Optional.of(appUser);
            }
        }
        return Optional.empty();
    }
        //Einfache Email-Validation
        private boolean isValidEmail(String email){
        if (email == null) return false;
        Pattern p = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
            return p.matcher(email).matches();
        }

        //Prüft ob ein Benutzername verfügbar ist
        public boolean isBenutzernameAvailable(String benutzername){
        return benutzername != null && !userRepository.existsByBenutzername(benutzername);
        }

        //Prüft ob eine Mail verfügbar ist
        public boolean isEmailAvailable (String email){
        return email != null && !userRepository.existsByEmail(email);
        }


    }

