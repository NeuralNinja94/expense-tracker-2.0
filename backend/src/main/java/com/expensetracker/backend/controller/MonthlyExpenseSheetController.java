package com.expensetracker.backend.controller;

import com.expensetracker.backend.dto.MonthlyExpenseSheet;
import com.expensetracker.backend.services.MonthlyExpenseSheetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/{userId}/expense-sheet/monthly")
public class MonthlyExpenseSheetController {

    private final MonthlyExpenseSheetService monthlyExpenseSheetService;

    public MonthlyExpenseSheetController(MonthlyExpenseSheetService monthlyExpenseSheetService) {
        this.monthlyExpenseSheetService = monthlyExpenseSheetService;
    }

    @GetMapping("/{year}/{month}")
    public MonthlyExpenseSheet getMonthlySheet(
            @PathVariable Long userId,
            @PathVariable int year,
            @PathVariable int month
    ) {
        return monthlyExpenseSheetService.generateMonthlySheet(userId, year, month);
    }


}
