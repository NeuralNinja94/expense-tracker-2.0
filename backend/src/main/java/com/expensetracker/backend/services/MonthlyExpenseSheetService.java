package com.expensetracker.backend.services;

import com.expensetracker.backend.dto.MonthlyExpenseSheet;
import com.expensetracker.backend.entities.Expense;
import com.expensetracker.backend.exception.BadRequestException;
import com.expensetracker.backend.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MonthlyExpenseSheetService {

    private final ExpenseService expenseService;

    public MonthlyExpenseSheetService(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public MonthlyExpenseSheet generateMonthlySheet(Long userId, int year, int month) {

        List<Expense> expenses = expenseService.getExpensesByMonth(userId, year, month);

        if(month < 1 || month > 12) {
            throw new BadRequestException("Monat muss zwischen 1 und 12 sein");
        }
        if (expenses.isEmpty()) {
            throw new NotFoundException("Keine Ausgaben für " + month + "/" + year + "gefunden." );
        }

        MonthlyExpenseSheet monthlyExpenseSheet = new MonthlyExpenseSheet();
        monthlyExpenseSheet.setYear(year);
        monthlyExpenseSheet.setMonth(month);
        monthlyExpenseSheet.setExpenses(expenses);

        //Gesamtsumme
        monthlyExpenseSheet.setTotal(
                expenses.stream().mapToDouble(Expense::getBetrag).sum()
        );

        //Höchste Ausgabe
        monthlyExpenseSheet.setHighest(
                expenses.stream()
                        .max(Comparator.comparing(Expense::getBetrag))
                        .orElse(null)

        );

        //Tiefste Ausgabe
        monthlyExpenseSheet.setLowest(
                expenses.stream()
                        .min(Comparator.comparing(Expense::getBetrag))
                        .orElse(null)
        );
        //Nach Kategorien Summieren
        Map<String, Double> categories =new HashMap<>();
        for (Expense e : expenses){
            categories.merge(e.getKategorie(), e.getBetrag(), Double::sum);
        }
        monthlyExpenseSheet.setCategories(categories);

        return monthlyExpenseSheet;


    }
}
