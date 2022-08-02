package dev.zheng.services.expenseService;

import dev.zheng.daos.expenseDAO.ExpenseDAO;
import dev.zheng.entities.Expense;
import dev.zheng.services.employeeService.exceptions.InvalidEmployeeIdException;
import dev.zheng.services.expenseService.exceptions.EmptyDescriptionException;
import dev.zheng.services.expenseService.exceptions.IllegalAmountException;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService{
    ExpenseDAO expenseDAO;

    public ExpenseServiceImpl(ExpenseDAO expenseDAO){this.expenseDAO = expenseDAO;}
    private void checkNullAndAmount(Expense e){
        if (e.getDescription() == null){
            throw new EmptyDescriptionException("The description cannot be empty");
        }
        if(e.getAmount() <= 0){
            throw new IllegalAmountException("Amount should not be zero or negative");
        }
        if(e.getEmployeeId() <= 0){
            throw new InvalidEmployeeIdException("Invalid employeeId");
        }
    }

    @Override
    public Expense addExpense(Expense e) {
        checkNullAndAmount(e);
        return expenseDAO.createExpense(e);
    }

    @Override
    public Expense retrieveExpenseById(int id) {
        return expenseDAO.getOneExpense(id);
    }

    @Override
    public List<Expense> retrieveAllExpenses() {
        return expenseDAO.getAllExpense();
    }

    @Override
    public boolean removeExpense(int id) {
        return false;
    }

    @Override
    public Expense modifyExpense(Expense e) {

        checkNullAndAmount(e);
        return expenseDAO.updateExpense(e);
    }

    @Override
    public Expense changeExpenseStatus(Expense e) {
        return null;
    }
}
