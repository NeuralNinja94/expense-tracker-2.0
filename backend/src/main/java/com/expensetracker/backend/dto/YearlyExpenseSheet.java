package com.expensetracker.backend.dto;

import com.expensetracker.backend.entities.Expense;

import java.util.List;
import java.util.Map;

public class YearlyExpenseSheet {
    private int year;
    private double total;
    private Expense highest;
    private Expense lowest;
    private Map<Integer, Double> monthlyTotals;
    private Map<String, Double> categories;
    private List<Expense> expenses;

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public double getTotal() {
        return getTotal();
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public Expense getHighest() {
        return highest;
    }
    public void setHighest(Expense highest) {
        this.highest = highest;
    }
    public Expense getLowest() {
        return lowest;
    }
    public void setLowest(Expense lowest) {
        this.lowest = lowest;
    }
    public Map<Integer, Double> getMonthlyTotals() {
        return monthlyTotals;
    }
    public void setMonthlyTotals(Map<Integer, Double> monthlyTotals) {
        this.monthlyTotals = monthlyTotals;
    }
    public Map<String, Double> getCategories() {
        return categories;
    }
    public void setCategories(Map<String, Double> categories) {
        this.categories = categories;
    }
    public List<Expense> getExpenses() {
        return expenses;
    }
    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }



}

