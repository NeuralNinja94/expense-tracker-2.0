package com.expensetracker.backend.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;


@Entity

public class ExpenseSheet {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@NotNull
@DecimalMin("0.1")
private double budget;
@NotNull
@PastOrPresent
private LocalDate monat;

@ManyToOne
private AppUser appUser;

@OneToMany
private List<Expense> expenses;

// Getter und Setter
public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public double getBudget() {
    return budget;
}

public void setBudget(double budget) {
    this.budget = budget;
}
public LocalDate getMonat() {
    return monat;
}

public void setMonat(LocalDate monat) {
    this.monat = monat;
}

public AppUser getUser() {
    return appUser;
}
public void setUser(AppUser appUser) {
    this.appUser = appUser;
}

public List<Expense> getExpenses() {
    return expenses;
}
public void setExpenses(List<Expense> expenses) {
    this.expenses = expenses;
}


}
