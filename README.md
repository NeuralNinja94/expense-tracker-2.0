# Expense Tracker 2.0

Ein persönliches Ausgabenverwaltungssystem, entwickelt mit Java Spring Boot.  
Das Projekt dient als Lern‑ und Praxisprojekt im Rahmen meiner Ausbildung zum **Informatiker EFZ – Applikationsentwicklung** an der WISS Zürich.

---

## Aktueller Stand

### ✔ Bereits umgesetzt
- Grundstruktur des Backends (Controller → Service → Repository)
- Passwort‑Hashing (BCrypt)
- Datenbankanbindung mit MySQL
- Erste REST-Endpoints für Ausgaben (noch nicht vollständig getestet)
- Saubere Projektstruktur
- Beispiel‑Konfigurationsdatei (`application.properties.example`)
- `.gitignore` schützt sensible Daten (z. B. echte `application.properties`)

### In Arbeit / Geplant
- Testen und validieren der bestehenden Endpoints
- Benutzerverwaltung (Login / Registrierung)
- Kategorien für Ausgaben
- Summenberechnung
- Frontend (React / Vite)
- Export‑Funktion (PDF/CSV)
- Swagger‑Dokumentation

---

##  Technologien

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **MySQL**
- **Maven**
- **BCrypt**

---

## Projektstruktur

backend/
├── controller/
├── service/
├── repository/
├── model/
├── config/
└── ExpenseTrackerApplication.java

## Installation & Setup

### 1. Repository klonen
git clone https://github.com/NeuralNinja94/expense-tracker-2.0.git

Konfigurationsdatei erstellen
backend/src/main/resources/application.properties.example
↓
backend/src/main/resources/application.properties

## Eigene MySQL-Datenbank-Zugangsdaten eintragen

(z. B. Host, Port, Username, Passwort)

## Anwendung starten

mvn spring-boot:run

## Ziel des Projekts

Dieses Projekt dient dazu:

meine Backend‑Fähigkeiten zu vertiefen

reale Software‑Architektur zu üben

ein vollständiges Projekt für Bewerbungen zu präsentieren

Clean Code, Git‑Flow und API‑Design zu trainieren

## Autor

Carmine Vaccaro  
Ausbildung: Informatiker EFZ – Applikationsentwicklung
WISS Zürich






