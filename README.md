# Expense Tracker 2.0

Ein persönliches Ausgabenverwaltungssystem, entwickelt mit Java Spring Boot.  
Das Projekt dient als Lern- und Praxisprojekt im Rahmen meiner Ausbildung zum **Informatiker EFZ – Applikationsentwicklung** an der WISS Zürich.

---

## 🚀 Aktueller Stand

### ✔ Bereits umgesetzt
- Grundstruktur des Backends (Controller → Service → Repository)
- Passwort-Hashing (BCrypt)
- Datenbankanbindung mit MySQL
- Erste REST-Endpoints für Ausgaben (noch nicht vollständig getestet)
- Summenberechnung:
  - Gesamtausgaben pro User
  - Summe pro Kategorie
  - Monatliche Summe
  - Summe in einem Zeitraum
  - Höchste & niedrigste Ausgabe
- Saubere Projektstruktur
- Beispiel-Konfigurationsdatei (`application.properties.example`)
- `.gitignore` schützt sensible Daten (z. B. echte `application.properties`)
- Login-Funktion repariert
- Benutzerverwaltung abgeschlossen (Login / Registrierung
  

### In Arbeit / Geplant
- Testen und Validieren der bestehenden Endpoints
- Protected Routes abschliessen
- Kategorien für Ausgaben
- Frontend (React / Vite)
- Export-Funktion (PDF/CSV)
- Swagger-Dokumentation

---

###  Technologien

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **MySQL**
- **Maven**
- **BCrypt**

---



### Installation & Setup
### Repository klonen

git clone https://github.com/NeuralNinja94/expense-tracker-2.0.git


### 2. Konfigurationsdatei erstellen
backend/src/main/resources/application.properties.example
→ kopieren nach  
backend/src/main/resources/application.properties

Eigene MySQL-Datenbank-Zugangsdaten eintragen (Host, Port, Username, Passwort).

### 3. Anwendung starten

mvn-spring-boot:run



---

## Ziel des Projekts

Dieses Projekt dient dazu:

- meine Backend-Fähigkeiten zu vertiefen  
- reale Software-Architektur zu üben  
- ein vollständiges Projekt für Bewerbungen zu präsentieren  
- Clean Code, Git-Flow und API-Design zu trainieren  

---

##  Autor

**Carmine Vaccaro**  
Ausbildung: Informatiker EFZ – Applikationsentwicklung  
WISS Zürich



