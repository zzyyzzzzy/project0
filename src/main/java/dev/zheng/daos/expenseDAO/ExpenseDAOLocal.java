package dev.zheng.daos.expenseDAO;

import dev.zheng.entities.Expense;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseDAOLocal implements ExpenseDAO {

    public static Map<Integer, Expense> expenseTable = new HashMap<>();
    private int id = 1;

    @Override
    public Expense createExpense(Expense e) {
        e.setId(id);
        expenseTable.put(e.getId(), e);
        id ++;
        return e;
    }

    @Override
    public Expense updateExpense(Expense e) {
        return null;
    }

    @Override
    public Expense getOneExpense(int id) {
        return null;
    }

    @Override
    public List<Expense> getAllExpense() {
        return null;
    }

    @Override
    public boolean deleteExpense(int id) {
        return false;
    }
}
