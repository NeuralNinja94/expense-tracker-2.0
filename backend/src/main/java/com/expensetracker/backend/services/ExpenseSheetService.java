package com.expensetracker.backend.services;
import java.util.List;

import com.expensetracker.backend.entities.AppUser;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.expensetracker.backend.entities.ExpenseSheet;
import com.expensetracker.backend.repositories.ExpenseSheetRepository;
import com.expensetracker.backend.repositories.AppUserRepository;



@Service
public class ExpenseSheetService {
    private final ExpenseSheetRepository expenseSheetRepository;
    private final AppUserRepository appUserRepository;

    public ExpenseSheetService(ExpenseSheetRepository expenseSheetRepository, AppUserRepository appUserRepository) {
        this.expenseSheetRepository = expenseSheetRepository;
        this.appUserRepository = appUserRepository;
    }

    // Methode zum Löschen eines Ausgabenblatts
    public void deleteExpenseSheet(@NonNull Long sheetId) {
        expenseSheetRepository.deleteById(sheetId);
    }

    //Methode zum Aktualisieren eines Ausgabenblatts
    public ExpenseSheet updateExpenseSheet(@NonNull Long sheetId, ExpenseSheet updatedExpenseSheet) {
        ExpenseSheet existingExpenseSheet = expenseSheetRepository.findById(sheetId)
                .orElseThrow(() -> new IllegalArgumentException("Ausgabenblatt nicht gefunden mit der ID: " + sheetId));

        existingExpenseSheet.setBudget(updatedExpenseSheet.getBudget());
        existingExpenseSheet.setMonat(updatedExpenseSheet.getMonat());

        Long userId = updatedExpenseSheet.getUser() != null ? updatedExpenseSheet.getUser().getId() : null;
        if (userId != null) {
            AppUser appUser = appUserRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden mit der ID: " + userId));
            existingExpenseSheet.setUser(appUser);
        }

        return expenseSheetRepository.save(existingExpenseSheet);
    }
    //Methode zum Abrufen eines Ausgabenblatts nach ID
    public ExpenseSheet getExpenseSheetById(@NonNull Long sheetId) {
        return expenseSheetRepository.findById(sheetId)
                .orElseThrow(() -> new IllegalArgumentException("Ausgabenblatt nicht gefunden mit der ID: " + sheetId));
    }
    
    
    //Methode zum Abrufen aller Ausgabenblätter mit optionalen Filtern
    public List<ExpenseSheet> getAllExpenseSheets(Long userId, Integer year, Integer month, String title, Double budget) {
        return expenseSheetRepository.findAll();
    }

    //Neuerstellung eines Ausgabenblatts
    public ExpenseSheet createExpenseSheet(ExpenseSheet expenseSheet) {
        Long userId = expenseSheet.getUser() != null ? expenseSheet.getUser().getId() : null;
        if (userId == null) {
            throw new IllegalArgumentException("Benutzer-ID muss gesetzt sein");
        }
        AppUser appUser = appUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden mit der ID: " + userId));
        expenseSheet.setUser(appUser);
        return expenseSheetRepository.save(expenseSheet);
    }

    //Methode zur Berechnung der Gesamtausgaben in einem Ausgabenblatt
    public double getTotalExpenses(@NonNull Long sheetId) {
        ExpenseSheet expenseSheet = expenseSheetRepository.findById(sheetId)
                .orElseThrow(() -> new IllegalArgumentException("Ausgabenblatt nicht gefunden mit der ID: " + sheetId));

        return expenseSheet.getExpenses()
                .stream()
                .mapToDouble(expense -> expense.getBetrag())
                .sum();
    }
    //Methode zur Überprüfung, ob das Budget überschritten wurde
    public boolean isBudgetExceeded(@NonNull Long sheetId) {
        ExpenseSheet expenseSheet = expenseSheetRepository.findById(sheetId)
                .orElseThrow(() -> new IllegalArgumentException("Ausgabenblatt nicht gefunden mit der ID: " + sheetId));

        double totalExpenses = getTotalExpenses(sheetId);
        return totalExpenses > expenseSheet.getBudget();
    }

    


    
}
