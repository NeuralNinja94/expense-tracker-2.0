package com.expensetracker.backend;

import com.expensetracker.backend.repositories.AppUserRepository;
import com.expensetracker.backend.repositories.ExpenseRepository;
import com.expensetracker.backend.services.ExpenseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNull;

public class ExpenseServiceTest {

    @Test
    void testCalculateTotalExpense(){

        ExpenseRepository expenseRepository = Mockito.mock(ExpenseRepository.class);
        AppUserRepository appUserRepository = Mockito.mock(AppUserRepository.class);

        ExpenseService service = new ExpenseService(expenseRepository, appUserRepository);

        Double result = service.calculateTotalExpense(1L);

        assertNull(result);
    }
}
