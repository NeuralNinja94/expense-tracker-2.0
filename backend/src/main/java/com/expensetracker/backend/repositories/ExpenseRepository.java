package com.expensetracker.backend.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetracker.backend.entities.Expense;



public interface  ExpenseRepository extends JpaRepository<Expense, Long> {

   // Zeigt alle Ausgaben eines bestimmten Users an
   List<Expense> findByAppUser_Id(Long userId);

   // Zeigt alle Ausgaben eines bestimmten Users in einem bestimmten Zeitraum an
   List<Expense> findByAppUser_IdAndDatumBetween(Long userId, java.time.LocalDate startDatum, java.time.LocalDate endDatum);

   // Zeigt alle Ausgaben eines bestimmten Users in einer bestimmten Kategorie an
   List<Expense> findByAppUser_IdAndKategorie(Long userId, String kategorie);

   // Zeigt alle Ausgaben eines bestimmten Users mit einem bestimmten Titel und Kategorie an
   List<Expense> findByAppUser_IdAndKategorieAndTitel(Long userId, String kategorie, String titel);

   // Zeigt eine bestimmte Ausgabe eines bestimmten Users an
   List<Expense> findByIdAndAppUserId(Long id, Long userId);

   Expense findTopByAppUser_IdOrderByBetragDesc(Long userId);
   Expense findTopByAppUser_IdOrderByBetragAsc(Long userId);



}
