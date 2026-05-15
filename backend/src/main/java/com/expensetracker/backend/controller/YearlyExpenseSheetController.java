package com.expensetracker.backend.controller;

import com.expensetracker.backend.dto.YearlyExpenseSheet;
import com.expensetracker.backend.services.YearlyExpenseSheetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/{userId}/expense-sheet/yearly")
public class YearlyExpenseSheetController {
    private final YearlyExpenseSheetService yearlyExpenseSheetService;

    public YearlyExpenseSheetController(YearlyExpenseSheetService yearlyExpenseSheetService) {
        this.yearlyExpenseSheetService = yearlyExpenseSheetService;
    }

    @GetMapping("/{year}")
    public YearlyExpenseSheet getYearlySheet(
            @PathVariable Long userId,
            @PathVariable int year
    ) {
        return yearlyExpenseSheetService.generateYearlySheet(userId, year);
    }

}
