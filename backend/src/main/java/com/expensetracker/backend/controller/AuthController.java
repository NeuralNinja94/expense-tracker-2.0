package com.expensetracker.backend.controller;

import com.expensetracker.backend.dto.LoginRequestDTO;
import com.expensetracker.backend.dto.LoginResponseDTO;
import com.expensetracker.backend.dto.RegisterRequestDTO;
import com.expensetracker.backend.entities.AppUser;
import com.expensetracker.backend.services.AppUserService;
import com.expensetracker.backend.services.JwtService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.aspectj.weaver.ast.Test;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AppUserService appUserService;
    private final JwtService jwtService;

    //Constructor Injection
    public AuthController(AppUserService appUserService, JwtService jwtService) {
        this.appUserService = appUserService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    //Registriert einen neuen User
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO request) {
        try {
            AppUser user = appUserService.registerUser(
                    request.getBenutzername(),
                    request.getEmail(),
                    request.getPasswort(),
                    null
            );
            Map<String, Object> body = Map.of(
                    "id",user.getId(),
                    "benutzername", user.getBenutzername(),
                    "email", user.getEmail(),
                    "role", user.getRole().name(),
                    "message", "Registrierung erfolgreich! Willkommen" + user.getBenutzername() +
                            "!"
            );
            return ResponseEntity.status(HttpStatus.OK).body(body);

        } catch (IllegalArgumentException ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error",ex.getMessage()));

        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "error", "Unerwarteter Fehler. Bitte versuche es später erneut."
            ));
        }

    }
    @PostMapping("login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginRequestDTO request) {
        try {
            Optional<AppUser> userOpt;
            if(request.getBenutzernameOrEmail().contains("@")){
                userOpt = appUserService.findByEmail(request.getBenutzernameOrEmail());
            } else {
                userOpt = appUserService.findByBenutzername(request.getBenutzernameOrEmail());
            }
            //User exstiert nicht
            if (userOpt.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Ungültige Anmeldedaten"));
            }
            AppUser user = userOpt.get();
            //2.Passwort prüfen mit authenticateUser
            Optional<AppUser> authenticatedUser = appUserService.authenticateUser(user.getUsername(),
                    request.getPasswort());
            if (authenticatedUser.isEmpty()){
                //passwort falsch
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Ungültige Anmeldedaten"));
            }
            //JWT Token generieren
            String token = jwtService.generateToken(
                    user.getBenutzername(),
                    user.getRole().name()
            );

            LoginResponseDTO response = new LoginResponseDTO(
                    token,
                    user.getId(),
                    user.getBenutzername(),
                    user.getEmail(),
                    user.getRole().name(),
                    86400000L
            );
            return ResponseEntity.ok(response);

        }catch(
         Exception e)
        {
            //unerwartete Fehler
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Ein Fehler ist aufgetreten: " + e.getMessage()));
        }
    }
    @GetMapping("/check-benutzername/{benutzername]")
    public ResponseEntity<Map<String, Object>> checkBenutzername(@PathVariable String benutzername) {

        boolean available = appUserService.isBenutzernameAvailable(benutzername);
        String message = available ? "Benutzername ist verfügbar" : "Benutzername bereits vergeben";
        return ResponseEntity.ok(Map.of("available", available, "message", message));
    }
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Map<String, Object>> checkEmail(@PathVariable String email) {

        boolean available = appUserService.isEmailAvailable(email);
        String message = available ? "Email ist verfügbar" : "Email bereits registriert";

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("available", available, "message", message));
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> test() {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("status", "ok", "endpoint", "/api/auth/test"));
    }


}
