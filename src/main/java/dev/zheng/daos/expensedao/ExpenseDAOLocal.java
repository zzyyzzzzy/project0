package dev.zheng.daos.expensedao;

import dev.zheng.entities.Expense;
import dev.zheng.entities.Status;

import java.util.*;

public class ExpenseDAOLocal implements ExpenseDAO {

    public static Map<Integer, Expense> expenseTable = new HashMap<>();
    private int id = 1;

    @Override
    public Expense createExpense(Expense e) {
        e.setId(id);
        e.setStatus(Status.PENDING);
        expenseTable.put(e.getId(), e);
        id ++;
        return e;
    }

    @Override
    public Expense updateExpense(Expense e) {
        expenseTable.put(e.getId(), e);
        return e;
    }

    @Override
    public Expense getOneExpense(int id) {
        return expenseTable.get(id);
    }

    @Override
    public List<Expense> getAllExpense() {
        Collection<Expense> allExpenseValues =  expenseTable.values();
        ArrayList<Expense> allExpense = new ArrayList<>(allExpenseValues);

        return allExpense ;
    }

    @Override
    public boolean deleteExpense(int id) {
        return false;
    }
}
