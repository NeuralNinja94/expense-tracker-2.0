package com.expensetracker.backend.repositories;



import java.util.Optional;

import com.expensetracker.backend.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppUserRepository extends JpaRepository<AppUser, Long>  {
// Benutzer nach Benutzernamen suchen
Optional<AppUser> findByBenutzername(String benutzername);

// Benutzer nach E-Mail suchen
Optional<AppUser> findByEmail(String email);

boolean existsByBenutzername(String benutzername);

boolean existsByEmail(String email);







}