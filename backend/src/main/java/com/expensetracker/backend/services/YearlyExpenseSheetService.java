package com.expensetracker.backend.services;

import com.expensetracker.backend.dto.YearlyExpenseSheet;
import com.expensetracker.backend.entities.Expense;
import com.expensetracker.backend.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YearlyExpenseSheetService {

    private final ExpenseService expenseService;

    public YearlyExpenseSheetService(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    public YearlyExpenseSheet generateYearlySheet(Long userId, int year){

        List<Expense> expenses = expenseService.getExpensesByYear(userId, year);

        if(expenses.isEmpty()){
            throw new NotFoundException("Für dieses Jahr" + year + "wurden keine Ausgaben gefunden.");
        }

        YearlyExpenseSheet yearlyExpenseSheet = new YearlyExpenseSheet();
        yearlyExpenseSheet.setYear(year);
        yearlyExpenseSheet.setExpenses(expenses);

        //Gesamtsumme
        yearlyExpenseSheet.setTotal(
                expenses.stream().mapToDouble(Expense::getBetrag).sum());

        //Höchste Ausgabe
        yearlyExpenseSheet.setHighest(
                expenses.stream()
                        .max(Comparator.comparing(Expense::getBetrag))
                        .orElse(null)
        );
        //tiefste Ausgabe
        yearlyExpenseSheet.setLowest(
                expenses.stream()
                        .min(Comparator.comparing(Expense::getBetrag))
                        .orElse(null)
        );
        //Monatssummen
        Map<Integer, Double> monthlyTotals = new HashMap<>();
        for(Expense e : expenses) {
            int month = e.getDatum().getMonthValue();
            monthlyTotals.merge(month, e.getBetrag(), Double::sum);
        }
        yearlyExpenseSheet.setMonthlyTotals(monthlyTotals);

        Map<String, Double> categories = new HashMap<>();
        for (Expense e : expenses) {
            categories.merge(e.getKategorie(), e.getBetrag(), Double::sum);
        }
        yearlyExpenseSheet.setCategories(categories);

        return yearlyExpenseSheet;
    }
}
