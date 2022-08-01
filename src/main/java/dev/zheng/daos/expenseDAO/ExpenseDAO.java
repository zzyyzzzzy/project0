package dev.zheng.daos.expenseDAO;

import dev.zheng.entities.Expense;

import java.util.List;

public interface ExpenseDAO {
    // curd

    // create
    Expense createExpense(Expense e);

    // update
    Expense updateExpense(Expense e);

    // read
    Expense getOneExpense(int id);
    List<Expense> getAllExpense();

    // delete
    boolean deleteExpense(int id);
}
