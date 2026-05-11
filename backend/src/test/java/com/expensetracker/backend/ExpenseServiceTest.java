package com.expensetracker.backend;

import com.expensetracker.backend.entities.Expense;
import com.expensetracker.backend.repositories.AppUserRepository;
import com.expensetracker.backend.repositories.ExpenseRepository;
import com.expensetracker.backend.services.ExpenseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private ExpenseService expenseService;

    private Expense createExpense(double betrag){
        Expense e = new Expense();
        e.setBetrag(betrag);
        return e;
    }
    @Test
    void testCalculateTotalExpenses() {
        List<Expense> mockExpenses = List.of(
                createExpense(10.0),
                createExpense(20.0),
                createExpense(5.0)
        );
        when(expenseRepository.findByAppUser_Id(1L)).thenReturn(mockExpenses);

        double result = expenseService.calculateTotalExpenses(1L);

        assertEquals(35.0, result);
    }
    @Test
     void testCalculateTotalByKategorieAndUser() {
        List<Expense> mockExpenses = List.of(
                createExpense(50.0),
                createExpense(25.0)
        );
        when(expenseRepository.findByAppUser_IdAndKategorie(1l, "Food"))
                .thenReturn(mockExpenses);

        double result = expenseService.calculateTotalByKategorieAndUser(1L, "Food");

        assertEquals(75.0, result);
    }

    @Test
    void TestCalculateTotalBetweenDatesByUser() {
        List<Expense> mockExpenses = List.of(
                createExpense(30.0),
                createExpense(20.0)
        );
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 1, 31);

        when(expenseRepository.findByAppUser_IdAndDatumBetween(1l, start, end))
                .thenReturn(mockExpenses);

        double result = expenseService.calculateTotalBetweenDatesByUser(1L, start, end);

        assertEquals(50.0, result);
    }


}
