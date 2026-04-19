package com.expensetracker.backend.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetracker.backend.entities.ExpenseSheet;


public interface ExpenseSheetRepository extends JpaRepository<ExpenseSheet, Long> {
    //Zeigt ein bestimmtes Sheet für bestimmten Monat + User
    List<ExpenseSheet> findByMonatAndAppUserId(LocalDate monat, Long userId);

    //Zeigt alle Sheets eines bestimmten Users an
    List<ExpenseSheet> findByAppUserId(Long userId);

    //Zeigt das aktuellste Sheet eines Users an
    List<ExpenseSheet> findByAppUserIdOrderByMonatDesc(Long userId);
    
    //Prüft ob ein Sheet für bestimmten Monat + User existiert
    boolean existsByMonatAndAppUserId(LocalDate monat, Long userId);

    // Zeigt alle Sheets eines Users eines bestimmten jahres
    List<ExpenseSheet> findByMonatBetweenAndAppUserIdOrderByMonatAsc(LocalDate startMonat, LocalDate endMonat, Long userId);




}
