package dev.zheng.daos;

import dev.zheng.entities.Expense;

import java.util.List;

public interface ExpenseDAO {
    // curd

    // create
    Expense createEmployee(Expense e);

    // update
    Expense updateEmployee(Expense e);

    // read
    Expense getOneExpense(int id);
    List<Expense> getAllExpense();

    // delete
    boolean deleteExpense(int id);
}
