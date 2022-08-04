package dev.zheng.daos.expensedao;

import dev.zheng.entities.Expense;
import dev.zheng.entities.Status;

import java.util.List;

public interface ExpenseDAO {
    // curd

    // create
    Expense createExpense(Expense e);

    // update
    Expense updateExpense(Expense e);
    Expense patchExpense(int id, Status status);

    // read
    Expense getOneExpense(int id);
    List<Expense> getAllExpense();

    // delete
    boolean deleteExpense(int id);
}
