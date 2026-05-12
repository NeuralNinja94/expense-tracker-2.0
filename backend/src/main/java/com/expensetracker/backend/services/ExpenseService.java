package com.expensetracker.backend.services;

import jakarta.transaction.Transactional;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.expensetracker.backend.entities.Expense;
import com.expensetracker.backend.entities.AppUser;
import com.expensetracker.backend.exception.ResourceNotFoundException;
import com.expensetracker.backend.repositories.ExpenseRepository;
import com.expensetracker.backend.repositories.AppUserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.lang.Long.sum;


@Service
@Transactional
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final AppUserRepository appUserRepository;
    
    public ExpenseService(ExpenseRepository expenseRepository, AppUserRepository appUserRepository) {
        this.expenseRepository = expenseRepository;
        this.appUserRepository = appUserRepository;
    }

    //Methode zum Abrufen aller Ausgaben
    public java.util.List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Methode zum Erstellen einer Ausgabe
    @SuppressWarnings("null")
    public Expense createExpense(Expense expense) {
        // Setze den User, falls nur die ID vorhanden ist
        if (expense.getUser() != null && expense.getUser().getId() != null) {
            Long userId = expense.getUser().getId();
            AppUser appUser = appUserRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Benutzer nicht gefunden mit der ID: " + userId));
            expense.setUser(appUser);
        }
        return expenseRepository.save(expense);
    }

    // Methode zum Aktualisieren einer Expense
    @SuppressWarnings("null")
    public Expense updateExpense(@NonNull Long expenseId, Expense updatedExpense) {
        // Bestehende Expense laden oder Fehler werfen
        Expense existingExpense = expenseRepository.findById(expenseId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Expense not found with id: " + expenseId)
            );

        // Aktualisiere die Felder
        existingExpense.setTitel(updatedExpense.getTitel());
        existingExpense.setBetrag(updatedExpense.getBetrag());
        existingExpense.setKategorie(updatedExpense.getKategorie());
        existingExpense.setDatum(updatedExpense.getDatum());

        // Aktualisiere User, falls vorhanden
        if (updatedExpense.getUser() != null && updatedExpense.getUser().getId() != null) {
            Long userId = updatedExpense.getUser().getId();
            AppUser appUser = appUserRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Benutzer nicht gefunden mit der ID: " + userId));
            existingExpense.setUser(appUser);
        }

        // Speichern und zurückgeben
        return expenseRepository.save(existingExpense);
    }
    // Methode zum Abrufen einer Ausgabe nach ID
    public Expense getExpenseById(@NonNull Long expenseId) {
        return expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + expenseId));
    }

     // Methode zum Löschen einer Ausgabe
    public void deleteExpense(@NonNull Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }

    //Gesamtausgaben berechnung
    public Double calculateTotalExpenses(Long userId) {
        List<Expense> expenses = expenseRepository.findByAppUser_Id(userId);

        return expenses.stream()
                .mapToDouble(Expense::getBetrag)
                .sum();
    }

    //Nach Kategorien summieren
    public Double calculateTotalByKategorieAndUser(String kategorie, Long userId){
        List<Expense> expenses = expenseRepository.findByAppUser_IdAndKategorie(userId, kategorie);

        return expenses.stream()
                .mapToDouble(Expense::getBetrag)
                .sum();
    }

    //Nach Monat Summieren
    public Double calculateMonthlyTotalByUser(Long userId, int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<Expense> expenses = expenseRepository.findByAppUser_IdAndDatumBetween(userId, start, end);

        return expenses.stream()
                .mapToDouble(Expense::getBetrag)
                .sum();
    }
    //nach Zeitraum summieren
    public Double calculateTotalBetweenDatesByUser(Long userId, LocalDate start, LocalDate end){
        List<Expense> expenses = expenseRepository.findByAppUser_IdAndDatumBetween(userId,start,end);

        return expenses.stream()
                .mapToDouble(Expense::getBetrag)
                .sum();
    }
    //Höchste Ausgabe eines Users
    public Expense findHighestExpense(Long userId){
        return expenseRepository.findTopByAppUser_IdOrderByBetragDesc(userId)
                .orElse(null);

    }
    //Niedrigste Ausgabe eines Users
    public Expense findLowestExpense(Long userId){
        return expenseRepository.findTopByAppUser_IdOrderByBetragAsc(userId)
                .orElse(null);

    }
    }




    