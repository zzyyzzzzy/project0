package dev.zheng.services.expenseservice;

import dev.zheng.entities.Expense;

import java.util.List;

public interface ExpenseService {
    Expense addExpense(Expense e);

    Expense retrieveExpenseById(int id);
    List<Expense> retrieveAllExpenses();
    List<Expense> retrieveAllExpensesByStatus(String status);
    boolean removeExpense(int id);
    Expense modifyExpense(Expense e);
    Expense changeExpenseStatus(Expense e);
}
