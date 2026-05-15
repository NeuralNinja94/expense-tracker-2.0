package com.expensetracker.backend.controller;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.expensetracker.backend.dto.ExpenseDto;
import com.expensetracker.backend.entities.Expense;
import com.expensetracker.backend.mapper.ExpenseMapper;
import com.expensetracker.backend.services.ExpenseService;







@RestController
@Validated
@RequestMapping("/api/users/{userId}/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ExpenseMapper expenseMapper;

    public ExpenseController(ExpenseService expenseService, ExpenseMapper expenseMapper) {
        this.expenseService = expenseService;
        this.expenseMapper = expenseMapper;
    }
    //Abrufen aller Ausgaben
    @GetMapping
    public List<ExpenseDto> getAllExpenses() {
        return expenseService.getAllExpenses().stream()
                .map(expenseMapper::toDto)
                .collect(Collectors.toList());
    }
    //Abrufen einer Ausgabe nach Id
    @GetMapping("/{expenseId}")
    public ExpenseDto getExpenseById(@PathVariable @NonNull Long expenseId) {
        return expenseMapper.toDto(expenseService.getExpenseById(expenseId));
    }

    //Methode zum erstellen einer Ausgabe
    @PostMapping
    public ExpenseDto createExpense(@PathVariable Long userId, @RequestBody @Validated ExpenseDto expenseDto) {
        Expense expense = expenseMapper.toEntity(expenseDto);
        return expenseMapper.toDto(expenseService.createExpense(expense));
    }

    //Methode zum Aktualisieren einer Ausgabe
    @PutMapping("/{expenseId}")
    public ExpenseDto updateExpense(@PathVariable Long expenseId, @RequestBody @Validated ExpenseDto expenseDto) {
        Expense expense = expenseMapper.toEntity(expenseDto);
        return expenseMapper.toDto(expenseService.updateExpense(expenseId, expense));
    }
    //Methode zum Löschen einer Ausgabe
    @DeleteMapping("/{expenseId}")
    public void deleteExpense(@PathVariable Long expenseId) {

        expenseService.deleteExpense(expenseId);
    }

    //Gesamtausgaben
    @GetMapping ("/total")
    public Double getTotal(@PathVariable Long userId) {
        return expenseService.calculateTotalExpenses(userId);
    }
    //Summe der Kategorien
    @GetMapping("/category/{kategorie}")
    public Double getTotalByCategory(@PathVariable Long userId, @PathVariable String kategorie) {
        return expenseService.calculateTotalByKategorieAndUser(kategorie, userId);
    }
    //Monatssumme
    @GetMapping("/month/{year}/{month}")
    public Double getMonthlyTotal(@PathVariable Long userId, @PathVariable int year, @PathVariable int month) {
        return expenseService.calculateMonthlyTotalByUser(userId, year, month);

    }
    //Summe nach Zeitraum
    @GetMapping("/range")
    public Double getTotalInRange(
            @PathVariable Long userId,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end){
        return expenseService.calculateTotalBetweenDatesByUser(userId, start, end);
    }
    //Höchste Ausgabe
    @GetMapping("/highest")
    public ExpenseDto getHighest(@PathVariable Long userId){
        return expenseMapper.toDto(expenseService.findHighestExpense(userId));
    }

    //niedrigste Ausgabe
    @GetMapping("/lowest")
    public ExpenseDto getLowest(@PathVariable Long userId) {
        return expenseMapper.toDto(expenseService.findLowestExpense(userId));
    }


    
    


}


    
    



    
    

